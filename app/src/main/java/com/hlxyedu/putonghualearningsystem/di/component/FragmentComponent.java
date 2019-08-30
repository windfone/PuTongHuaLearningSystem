package com.hlxyedu.putonghualearningsystem.di.component;

import android.app.Activity;

import com.hlxyedu.putonghualearningsystem.di.module.FragmentModule;
import com.hlxyedu.putonghualearningsystem.di.scope.FragmentScope;
import com.hlxyedu.putonghualearningsystem.ui.exam.fragment.EChineseCharacterFragment;
import com.hlxyedu.putonghualearningsystem.ui.exam.fragment.EScoreFragment;
import com.hlxyedu.putonghualearningsystem.ui.exam.fragment.EShortEssayFragment;
import com.hlxyedu.putonghualearningsystem.ui.exam.fragment.EWordFragment;
import com.hlxyedu.putonghualearningsystem.ui.exam.fragment.EZuoWenFragment;
import com.hlxyedu.putonghualearningsystem.ui.main.fragment.FamousClassroomFragment;
import com.hlxyedu.putonghualearningsystem.ui.main.fragment.OnLineLearningFragment;
import com.hlxyedu.putonghualearningsystem.ui.main.fragment.ExamCenterFragment;
import com.hlxyedu.putonghualearningsystem.ui.main.fragment.PersonalCenterFragment;
import com.hlxyedu.putonghualearningsystem.ui.onlinelearning.fragment.ChildSoundFragment;
import com.hlxyedu.putonghualearningsystem.ui.onlinelearning.fragment.DetailEssayFragment;
import com.hlxyedu.putonghualearningsystem.ui.onlinelearning.fragment.DetailHanZiFragment;
import com.hlxyedu.putonghualearningsystem.ui.onlinelearning.fragment.DetailWordFragment;
import com.hlxyedu.putonghualearningsystem.ui.onlinelearning.fragment.HanZiLearningFragment;
import com.hlxyedu.putonghualearningsystem.ui.onlinelearning.fragment.PinYinLearningFragment;
import com.hlxyedu.putonghualearningsystem.ui.onlinelearning.fragment.ShortEssayFragment;
import com.hlxyedu.putonghualearningsystem.ui.onlinelearning.fragment.SoftWordFragment;
import com.hlxyedu.putonghualearningsystem.ui.onlinelearning.fragment.WordFollowFragment;
import com.hlxyedu.putonghualearningsystem.ui.teacherclass.fragment.ExerciseFragment;
import com.hlxyedu.putonghualearningsystem.ui.publics.fragment.ViewPagerFragment;

import dagger.Component;

/**
 * Created by skyworth on 16/8/7.
 */

@FragmentScope
@Component(dependencies = AppComponent.class, modules = FragmentModule.class)
public interface FragmentComponent {

    Activity getActivity();

    void inject(OnLineLearningFragment onLineLearningFragment);

    void inject(ExamCenterFragment examCenterFragment);

    void inject(FamousClassroomFragment famousClassroomFragment);

    void inject(PersonalCenterFragment personalCenterFragment);

    void inject(PinYinLearningFragment pinYinLearningFragment);

    void inject(HanZiLearningFragment hanZiLearningFragment);

    void inject(WordFollowFragment wordFollowFragment);

    void inject(ShortEssayFragment shortEssayFragment);

    void inject(SoftWordFragment softWordFragment);

    void inject(ChildSoundFragment childSoundFragment);

    void inject(ExerciseFragment exerciseFragment);

    void inject(ViewPagerFragment viewPagerFragment);

    void inject(DetailEssayFragment detailEssayFragment);

    void inject(DetailWordFragment detailWordFragment);

    void inject(DetailHanZiFragment detailHanZiFragment);

    void inject(EChineseCharacterFragment eChineseCharacterFragment);

    void inject(EWordFragment eWordFragment);

    void inject(EShortEssayFragment eShortEssayFragment);

    void inject(EZuoWenFragment eZuoWenFragment);

    void inject(EScoreFragment eScoreFragment);

}
