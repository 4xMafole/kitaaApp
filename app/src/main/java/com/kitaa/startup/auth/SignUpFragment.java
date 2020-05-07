package com.kitaa.startup.auth;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.kitaa.R;
import com.kitaa.startup.MainActivity;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 */
public class SignUpFragment extends Fragment
{

    private TextView _signInTxt;
    private EditText _email, _fullname, _password, _conformPassword;
    private ImageButton _closeButton;
    private Button _signUpBtn;
    private ProgressBar _progressBar;

    private FrameLayout _parentFrameLayout;

    private FirebaseAuth _firebaseAuth;
    private FirebaseFirestore _firebaseFireStore;
    private String _emailPattern = "[a-zA-Z0-9._-]+@[a-z]+.[a-z]+";

    public SignUpFragment()
    {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_sign_up, container, false);
        _parentFrameLayout = requireActivity().findViewById(R.id.register_framelayout);

        _email = view.findViewById(R.id.sign_up_email);
        _fullname = view.findViewById(R.id.sign_up_name);
        _password = view.findViewById(R.id.sign_up_password);
        _conformPassword = view.findViewById(R.id.sign_up_password_conform);
        _signInTxt = view.findViewById(R.id.user_sign_in_txt);

        _closeButton = view.findViewById(R.id.sign_up_btn_close);
        _progressBar = view.findViewById(R.id.sign_up_progressbar);
        _signUpBtn = view.findViewById(R.id.sign_up_btn);

        _firebaseAuth = FirebaseAuth.getInstance();
        _firebaseFireStore = FirebaseFirestore.getInstance();

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);

        _signInTxt.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                setFragment(new  SignInFragment());
            }
        });
        _signUpBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                checkEmailAndPassword();
            }
        });
        _closeButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                mainIntent();
            }
        });

        _email.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after)
            {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {
                checkInputs();
            }

            @Override
            public void afterTextChanged(Editable s)
            {

            }
        });
        _fullname.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after)
            {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {
                checkInputs();
            }

            @Override
            public void afterTextChanged(Editable s)
            {

            }
        });
        _password.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after)
            {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {
                checkInputs();
            }

            @Override
            public void afterTextChanged(Editable s)
            {

            }
        });
        _conformPassword.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after)
            {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {
                checkInputs();
            }

            @Override
            public void afterTextChanged(Editable s)
            {

            }
        });

    }

    private void mainIntent()
    {
        Intent mainIntent = new Intent(getActivity(), MainActivity.class);
        startActivity(mainIntent);
        requireActivity().finish();
    }

    private void checkEmailAndPassword()
    {
        if(_email.getText().toString().matches(_emailPattern))
        {
            if(_password.getText().toString().equals(_conformPassword.getText().toString()))
            {
                _progressBar.setVisibility(View.VISIBLE);
                disabledButton();

                _firebaseAuth.createUserWithEmailAndPassword(_email.getText().toString(), _password.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>()
                {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task)
                    {
                        if(task.isSuccessful())
                        {
                            saveUserData();
                        }
                        else
                        {
                            _progressBar.setVisibility(View.INVISIBLE);
                            enabledButton();
                            String error = Objects.requireNonNull(task.getException()).getMessage();
                            Toast.makeText(getActivity(), error, Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
            else
            {
                _conformPassword.setError(getResources().getString(R.string.unmatched_passwords));
            }
        }
        else
        {
            _email.setError(getResources().getString(R.string.invalid_email));
        }
    }

    private void saveUserData()
    {
        Map<Object, String> _userData =new HashMap<>();
        _userData.put("fullname", _fullname.getText().toString());

        _firebaseFireStore.collection("USERS").add(_userData).addOnCompleteListener(new OnCompleteListener<DocumentReference>()
        {
            @Override
            public void onComplete(@NonNull Task<DocumentReference> task)
            {
                if(task.isSuccessful())
                {
                    mainIntent();
                }
                else
                {
                    _progressBar.setVisibility(View.INVISIBLE);
                    enabledButton();
                    String error = Objects.requireNonNull(task.getException()).getMessage();
                    Toast.makeText(getActivity(), error, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void checkInputs()
    {
        if(!TextUtils.isEmpty(_email.getText()))
        {
            if(!TextUtils.isEmpty(_fullname.getText()))
            {
                if(!TextUtils.isEmpty(_password.getText()) && _password.length() >= 8)
                {
                    if(!TextUtils.isEmpty(_conformPassword.getText()))
                    {
                        enabledButton();
                    }
                    else
                    {
                        disabledButton();
                    }
                }
                else
                {
                    disabledButton();
                }
            }
            else
            {
                disabledButton();
            }
        }
        else
        {
            disabledButton();
        }
    }

    @SuppressLint("ResourceAsColor")
    private void disabledButton()
    {
        _signUpBtn.setEnabled(false);
        _signUpBtn.setTextColor(R.color.disabledButton);
    }

    @SuppressLint("ResourceAsColor")
    private void enabledButton()
    {
        _signUpBtn.setEnabled(true);
        _signUpBtn.setTextColor(R.color.colorAccent);
    }

    private void setFragment(Fragment fragment)
    {
        FragmentTransaction _fragmentTransaction = requireActivity().getSupportFragmentManager().beginTransaction();
        _fragmentTransaction.setCustomAnimations(R.anim.slide_from_left, R.anim.slideout_from_right);
        _fragmentTransaction.replace(_parentFrameLayout.getId(), fragment);
        _fragmentTransaction.commit();
    }
}
