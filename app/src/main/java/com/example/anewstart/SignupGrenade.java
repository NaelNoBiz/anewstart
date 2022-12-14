package com.example.anewstart;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SignupGrenade#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SignupGrenade extends Fragment {
    private EditText etEmail, etPassword,etConfirm;
    private Button button;
    private FirebaseServices fbs;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SignupGrenade() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SignupGrenade.
     */
    // TODO: Rename and change types and number of parameters
    public static SignupGrenade newInstance(String param1, String param2) {
        SignupGrenade fragment = new SignupGrenade();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_signup_grenade, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        fbs=FirebaseServices.getInstance();
        etEmail=getView().findViewById(R.id.etEmail);
        etPassword=getView().findViewById(R.id.etPassword);
        etConfirm=getView().findViewById(R.id.etConfirm);

        button=getView().findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email=etEmail.getText().toString();
                String password=etPassword.getText().toString();
                String confirm=etConfirm.getText().toString();
                if(!email.trim().isEmpty()&&!password.trim().isEmpty()) {
                    if (CheckEmail(email)) {
                      if(CheckPass(password)){
                          if(password.trim()==confirm.trim()) {
                              fbs.getAuth().createUserWithEmailAndPassword(email, password).addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                                  @Override
                                  public void onComplete(@NonNull Task<AuthResult> task) {
                                      if(task.isSuccessful()){
                                          Toast.makeText(getActivity(),"Registration successful",Toast.LENGTH_SHORT).show();
                                      }
                                      else
                                      {
                                          Toast.makeText(getActivity(),"Registration unsuccessful",Toast.LENGTH_SHORT).show();
                                      }
                                  }
                              });
                          }
                          else{
                              Toast.makeText(getActivity(),"check confirm pass",Toast.LENGTH_SHORT).show();
                          }
                      }
                      else{
                          Toast.makeText(getActivity(),"check pass",Toast.LENGTH_SHORT).show();
                      }
                    }
                    else{
                        Toast.makeText(getActivity(),"check email",Toast.LENGTH_SHORT).show();
                    }

                }
                else{
                    Toast.makeText(getActivity(),"fields empty",Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    public boolean CheckEmail(String CHECK)
    {
        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(CHECK);
        return matcher.matches();
    }
    public boolean CheckPass(String CHECK)
    {
        Pattern pattern;
        Matcher matcher;
        final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{4,}$";
        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(CHECK);

        return matcher.matches();
    }


}