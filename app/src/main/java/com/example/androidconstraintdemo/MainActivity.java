package com.example.androidconstraintdemo;

import android.os.Bundle;
import android.transition.ChangeBounds;
import android.transition.TransitionManager;
import android.view.View;
import android.view.animation.AnticipateOvershootInterpolator;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

public class MainActivity extends AppCompatActivity {

    ConstraintLayout root;
    ImageView backgroundImage;
    boolean show = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hampi);

        root = findViewById(R.id.root);
        backgroundImage = findViewById(R.id.backgroundImage);

        backgroundImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (show)
                    // if the animation is shown, we hide back the views
                    hideComponents();
                else
                    // if the animation is NOT shown, we animate the views
                    showComponents();
            }
        });
    }


    private void showComponents() {

        show = true;
        ConstraintSet constraintSet = new ConstraintSet();
        constraintSet.clone(this, R.layout.hampi_detail);

        ChangeBounds transition = new ChangeBounds();
        transition.setInterpolator(new AnticipateOvershootInterpolator(1.0f));
        transition.setDuration(1200);

        TransitionManager.beginDelayedTransition(root, transition);

        //here root is the name of view to which we are applying the constraintSet
        constraintSet.applyTo(root);
    }

    private void hideComponents() {
        show = false;

        ConstraintSet constraintSet = new ConstraintSet();
        constraintSet.clone(this, R.layout.hampi);

        ChangeBounds transition = new ChangeBounds();
        transition.setInterpolator(new AnticipateOvershootInterpolator(1.0f));
        transition.setDuration(1200);

        TransitionManager.beginDelayedTransition(root, transition);

        constraintSet.applyTo(root);
    }


}
