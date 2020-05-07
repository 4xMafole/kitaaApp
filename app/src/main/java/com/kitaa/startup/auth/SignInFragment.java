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
import com.kitaa.R;
import com.kitaa.startup.MainActivity;

import java.util.Objects;

import static com.kitaa.startup.auth.RegisterActivity._onResetPasswordFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class SignInFragment extends Fragment
{

    public static final String INVALID_CREDENTIALS = "Incorrect e-mail or password";
    private TextView _signUp, _forgotPassword;
    private EditText _email, _password;
    private ImageButton _closeBtn;
    private ProgressBar _progressBar;
    private Button _signInBtn;
    private String _emailPattern = "[a-zA-Z0-9._-]+@[a-z]+.[a-z]+";


    private FrameLayout _parentFrameLayout;
    private FirebaseAuth _firebaseAuth;

    public SignInFragment()
    {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_sign_in, container, false);
        _parentFrameLayout = requireActivity().findViewById(R.id.register_framelayout);

        _signUp = view.findViewById(R.id.new_user);
        _email = view.findViewById(R.id.sign_in_email);
        _password = view.findViewById(R.id.sign_in_password);
        _closeBtn = view.findViewById(R.id.sign_in_btn_close);
        _signInBtn = view.findViewById(R.id.sign_in_btn);
        _progressBar = view.findViewById(R.id.sign_in_progressbar);
        _forgotPassword = view.findViewById(R.id.sign_in_forgot_password);

        _firebaseAuth = FirebaseAuth.getInstance();

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);

        _signUp.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                setFragment(new SignUpFragment());
            }
        });
        _signInBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                checkEmailAndPassword();
            }
        });
        _closeBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                mainIntent();
            }
        });
        _forgotPassword.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                _onResetPasswordFragment = true;
                setFragment(new ResetPasswordFragment());
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
            if(_password.length() >= 8)
            {
                _progressBar.setVisibility(View.VISIBLE);
                _firebaseAuth.signInWithEmailAndPassword(_email.getText().toString(), _password.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>()
                {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task)
                    {
                        if(task.isSuccessful())
                        {
                            mainIntent();
                        }
                        else
                        {
                            _progressBar.setVisibility(View.INVISIBLE);
                            String error = Objects.requireNonNull(task.getException()).getMessage();
                            Toast.makeText(getActivity(), error, Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
            else
            {
                Toast.makeText(getActivity(), INVALID_CREDENTIALS, Toast.LENGTH_SHORT).show();
            }
        }
        else
        {
                Toast.makeText(getActivity(), INVALID_CREDENTIALS, Toast.LENGTH_SHORT).show();
        }
    }

    private void checkInputs()
    {
        if(!TextUtils.isEmpty(_email.getText()))
        {
            if(!TextUtils.isEmpty(_password.getText()))
            {
                enableButton();
            }
            else
            {
                disableButton();
            }
        }
        else
        {
                disableButton();
        }
    }

    @SuppressLint("ResourceAsColor")
    private void disableButton()
    {
        _signInBtn.setEnabled(false);

        _signInBtn.setTextColor(R.color.disabledButton);
    }

    private void enableButton()
    {
        _signInBtn.setEnabled(true);
        //noinspection deprecation
        _signInBtn.setTextColor(getResources().getColor(R.color.colorAccent));
    }

    private void setFragment(Fragment fragment)
    {
        FragmentTransaction _fragmentTransaction = requireActivity().getSupportFragmentManager().beginTransaction();
        _fragmentTransaction.setCustomAnimations(R.anim.slide_from_right, R.anim.slideout_from_left);
        _fragmentTransaction.replace(_parentFrameLayout.getId(), fragment);
        _fragmentTransaction.commit();
    }
}
