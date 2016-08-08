package com.mahendra.shopperstock.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mahendra.shopperstock.IntroActivity;
import com.mahendra.shopperstock.MainActivity;
import com.mahendra.shopperstock.R;


public class MyFragment extends Fragment {

    public static final String EXTRA_MESSAGE = "EXTRA_MESSAGE";
    View v = null;
    private int mIndex;
    private TextView introSignin,introSignup;
    private SharedPreferences sharedPreferences;
    // public ResponseDataArray country;

    public MyFragment() {

        //  country = new ResponseDataArray();

    }

    public MyFragment(int index) {
        mIndex = index;
    }

    public static final MyFragment newInstance(String message, int index) {
        MyFragment f = new MyFragment(index);
        Bundle bdl = new Bundle(index);

        bdl.putString(EXTRA_MESSAGE, message);
        f.setArguments(bdl);
        return f;
    }

    @SuppressLint("NewApi")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,

                             Bundle savedInstanceState) {
        if (mIndex == 3) {
            v = inflater.inflate(R.layout.last_intro_signin_up_activity,
                    container, false);

            setBodyUI(v);

        } else {
            v = inflater.inflate(R.layout.first_intro_activity, container,
                    false);


            changeImageHIW(v, mIndex);
        }

        return v;

    }// onCreateView()................

    /**
     * Change background image on How It Work page
     *
     * @param v
     * @param pos
     */
    private void changeImageHIW(View v, final int pos) {
        TextView txtSecond = (TextView) v.findViewById(R.id.txtSubIntro);
        TextView txtone = (TextView) v.findViewById(R.id.txtSubIntro1);
        //Typeface HevltltstdBlk = Typeface.createFromAsset( getActivity().getAssets(),"fonts/HelveticaLTStd-Blk.otf");
        //Typeface HevltltstdRoman = Typeface.createFromAsset( getActivity().getAssets(),"fonts/HelveticaLTStd-Roman.otf");

       // txtone.setTypeface(HevltltstdBlk);
       // txtSecond.setTypeface(HevltltstdRoman);

        RelativeLayout rlayout = (RelativeLayout) v.findViewById(R.id.llMainBg);
        if (pos == 1) {
            txtSecond.setText("");//getResources().getString(R.string.intro_page_first_text));
            //  rlayout.setBackground(getResources().getDrawable(R.drawable.how_it_work_bg1));
        } else {
            txtSecond.setText("");//""getResources().getString(R.string.intro_page_second_text));
            // rlayout.setBackground(getResources().getDrawable(R.drawable.how_it_work_bg1));
        }
       /* getActivity().findViewById(R.id.IntroSkip).setOnClickListener(
                new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        IntroActivity.chnageUIonSkip();
                    }
                });*/

       /* v.findViewById(R.id.txtIntroNext).setOnClickListener(
                new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        IntroActivity.chnageUIonNext(pos);
                    }
                });*/
    }// end changeImageHIW().................

    /**
     * Inital click event and manage UI
     */
    private void setBodyUI(View v) {

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());

       // introSignin = (TextView) v.findViewById(R.id.introSignin);
        introSignup = (TextView) v.findViewById(R.id.introSignup);

       /* introSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences.Editor edit = sharedPreferences.edit();
                edit.putBoolean("isintroshow",true);
                edit.commit();

                *//*Intent intent = new Intent(getActivity(), SigninActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK);

                startActivity(intent);
                getActivity().finish();*//*

            }
        });*/
        introSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences.Editor edit = sharedPreferences.edit();
                edit.putBoolean("isintroshow",true);
                edit.commit();

               Intent in = new Intent(getActivity(), MainActivity.class);
                in.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                // in.putExtra("KEY", GCMKEY);
                startActivity(in);
                getActivity().finish();

            }
        });
     /*   letsstartTV = (TextView) v.findViewById(R.id.ltstartTV);
        letsstartTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                SharedPreferences.Editor edit = sharedPreferences.edit();
                edit.putBoolean("isintroshow",true);
                edit.commit();

                Intent newIntent = new Intent(getActivity(),LoginActivity.class);
                startActivity(newIntent);
                getActivity().finish();
                Utility.doAnim(getActivity(), "right");


            }
        });*/


        /*if(Utility.getUtilityInstance().isNetworkAvailable(getActivity())) {
            callServiceForGetCountry();
        }else{
            Utility.getUtilityInstance().showMessage(getResources().getString(R.string.check_network),getActivity());
        }*/

       /* v.findViewById(R.id.signup).setOnClickListener(
                new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        //      Bundle b = new Bundle();
                        //     b.putSerializable("country", country);
                        Utility.doStartActivityWithoutFinish(getActivity(), RegistrationActivity.class, "right");

                    }
                });

        v.findViewById(R.id.login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utility.SetPreferences(getActivity(), "isIntroPage", "1");
                Utility.getUtilityInstance().doStartActivityWithoutFinish(getActivity(), LoginActivity.class, "right");
            }
        });*/


    }// end setBodyUI()-----------


}// end main class-------------------------
