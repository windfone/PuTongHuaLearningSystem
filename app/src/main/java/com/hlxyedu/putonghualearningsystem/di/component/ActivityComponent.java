package com.hlxyedu.putonghualearningsystem.di.component;

import android.app.Activity;


import com.hlxyedu.putonghualearningsystem.di.module.ActivityModule;
import com.hlxyedu.putonghualearningsystem.di.scope.ActivityScope;
import com.hlxyedu.putonghualearningsystem.ui.essay.activity.EssayDetailsActivity;
import com.hlxyedu.putonghualearningsystem.ui.essay.activity.EssayListActivity;
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

    void inject(MainActivity mainActivity);

    void inject(LoginActivity loginActivity);

    void inject(EssayListActivity essayListActivity);

    void inject(EssayDetailsActivity essayDetailsActivity);

    void inject(PersonalStatisticsActivity personalStatisticsActivity);

    void inject(PersonalInfoActivity personalInfoActivity);

    void inject(GeneralPurposeActivity generalPurposeActivity);

    void inject(AboutUsActivity aboutUsActivity);

    void inject(FeedBackActivity feedBackActivity);

    void inject(EnterUsActivity enterUsActivity);

    void inject(ExerciseDetailActivity exerciseDetailActivity);

    void inject(OnLineLearnDetailsActivity onLineLearnDetailsActivity);
}
