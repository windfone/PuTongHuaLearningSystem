package com.hlxyedu.putonghualearningsystem.di.component;

import android.app.Activity;


import com.hlxyedu.putonghualearningsystem.di.module.ActivityModule;
import com.hlxyedu.putonghualearningsystem.di.scope.ActivityScope;
import com.hlxyedu.putonghualearningsystem.ui.home.activity.CertificateVerificationQueryActivity;
import com.hlxyedu.putonghualearningsystem.ui.home.activity.ExamRegistrationActivity;
import com.hlxyedu.putonghualearningsystem.ui.home.activity.ExamRegistrationNextActivity;
import com.hlxyedu.putonghualearningsystem.ui.home.activity.GradeQueryActivity;
import com.hlxyedu.putonghualearningsystem.ui.home.activity.RegistrationIntroductionActivity;
import com.hlxyedu.putonghualearningsystem.ui.home.activity.QueryActivity;
import com.hlxyedu.putonghualearningsystem.ui.home.activity.RegistrationQueryActivity;
import com.hlxyedu.putonghualearningsystem.ui.home.activity.TicketInquiryActivity;
import com.hlxyedu.putonghualearningsystem.ui.splash.activity.SplashActivity;
import com.hlxyedu.putonghualearningsystem.ui.exam.activity.ExamDetailActivity;
import com.hlxyedu.putonghualearningsystem.ui.login.activity.LoginActivity;
import com.hlxyedu.putonghualearningsystem.ui.main.activity.MainActivity;
import com.hlxyedu.putonghualearningsystem.ui.onlinelearning.activity.OnLineLearnDetailsActivity;
import com.hlxyedu.putonghualearningsystem.ui.personal.activity.AboutUsActivity;
import com.hlxyedu.putonghualearningsystem.ui.personal.activity.EnterUsActivity;
import com.hlxyedu.putonghualearningsystem.ui.personal.activity.FeedBackActivity;
import com.hlxyedu.putonghualearningsystem.ui.personal.activity.GeneralPurposeActivity;
import com.hlxyedu.putonghualearningsystem.ui.personal.activity.PersonalInfoActivity;
import com.hlxyedu.putonghualearningsystem.ui.personal.activity.PersonalStatisticsActivity;
import com.hlxyedu.putonghualearningsystem.ui.teacherclass.activity.ExerciseDetailActivity;

import dagger.Component;

/**
 * 作者：skyworth on 2017/7/10 16:04
 * 邮箱：dqwei@iflytek.com
 */
@ActivityScope
@Component(dependencies = AppComponent.class, modules = {ActivityModule.class})
public interface ActivityComponent {

    Activity getActivity();

    void inject(SplashActivity splashActivity);

    void inject(MainActivity mainActivity);

    void inject(LoginActivity loginActivity);

    void inject(PersonalStatisticsActivity personalStatisticsActivity);

    void inject(PersonalInfoActivity personalInfoActivity);

    void inject(GeneralPurposeActivity generalPurposeActivity);

    void inject(AboutUsActivity aboutUsActivity);

    void inject(FeedBackActivity feedBackActivity);

    void inject(EnterUsActivity enterUsActivity);

    void inject(ExerciseDetailActivity exerciseDetailActivity);

    void inject(OnLineLearnDetailsActivity onLineLearnDetailsActivity);

    void inject(ExamDetailActivity examDetailActivity);

    void inject(QueryActivity queryActivity);

    void inject(RegistrationIntroductionActivity registrationIntroductionActivity);

    void inject(ExamRegistrationActivity examRegistrationActivity);

    void inject(GradeQueryActivity gradeQueryActivity);

    void inject(ExamRegistrationNextActivity examRegistrationNextActivity);

    void inject(RegistrationQueryActivity registrationQueryActivity);

    void inject(TicketInquiryActivity ticketInquiryActivity);

    void inject(CertificateVerificationQueryActivity certificateVerificationQueryActivity);
}
