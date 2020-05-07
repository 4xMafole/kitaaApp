package com.kitaa.startup.auth;

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
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.kitaa.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ResetPasswordFragment extends Fragment
{

    private EditText _email;
    private TextView _notificationTxt, _signInTxt;
    private Button _resetPasswordBtn;
    private ProgressBar _progressBar;

    private FrameLayout _parentFrameLayout;
    private FirebaseAuth _firebaseAuth;

    public ResetPasswordFragment()
    {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_reset_password, container, false);

        _email = view.findViewById(R.id.forgot_passwrd_email);
        _notificationTxt = view.findViewById(R.id.notification_txt);
        _resetPasswordBtn = view.findViewById(R.id.reset_passwrd_btn);
        _progressBar = view.findViewById(R.id.reset_passwrd_progressbar);
        _signInTxt = view.findViewById(R.id.forgot_passwrd_user);

        _firebaseAuth = FirebaseAuth.getInstance();

        _parentFrameLayout = requireActivity().findViewById(R.id.register_framelayout);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);

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

        _signInTxt.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                setFragment(new SignInFragment());
            }
        });
        _resetPasswordBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                disableButton();
                _progressBar.setVisibility(View.VISIBLE);

                _firebaseAuth.sendPasswordResetEmail(_email.getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>()
                {
                    @Override
                    public void onComplete(@NonNull Task<Void> task)
                    {
                        if(task.isSuccessful())
                        {
                            _notificationTxt.setVisibility(View.VISIBLE);
                            _progressBar.setVisibility(View.GONE);
                        }
                        else
                        {

                            Exception error = task.getException();

                            if(error != null)
                            {
                                String networkError = getResources().getString(R.string.error_internet_connection);
                                _notificationTxt.setText(String.format("* %s", networkError));
                                //noinspection deprecation
                                _notificationTxt.setTextColor(getResources().getColor(R.color.error));
                                _notificationTxt.setVisibility(View.VISIBLE);
                                _progressBar.setVisibility(View.GONE);
                            }
                            else
                            {
                                _progressBar.setVisibility(View.GONE);
                                _notificationTxt.setVisibility(View.GONE);
                            }
                        }

                        enableButton();
                    }
                });
            }
        });

    }

    private void setFragment(Fragment fragment)
    {
        FragmentTransaction _fragmentTransaction = requireActivity().getSupportFragmentManager().beginTransaction();
        _fragmentTransaction.setCustomAnimations(R.anim.slide_from_right, R.anim.slideout_from_left);
        _fragmentTransaction.replace(_parentFrameLayout.getId(), fragment);
        _fragmentTransaction.commit();
    }

    private void checkInputs()
    {
        if(!TextUtils.isEmpty(_email.getText()))
        {
            enableButton();
        }
        else
        {
            disableButton();
        }
    }

    private void disableButton()
    {
        _resetPasswordBtn.setEnabled(false);
        //noinspection deprecation
        _resetPasswordBtn.setTextColor(getResources().getColor(R.color.disabledButton));
    }

    private void enableButton()
    {
        _resetPasswordBtn.setEnabled(true);
        //noinspection deprecation
        _resetPasswordBtn.setTextColor(getResources().getColor(R.color.colorAccent));
    }
}
