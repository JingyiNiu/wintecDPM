package com.wintec.degreemap.ui.manager.manage_courses;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.appcompat.app.AlertDialog;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import com.wintec.degreemap.R;
import com.wintec.degreemap.data.model.Course;
import com.wintec.degreemap.databinding.FragmentManagerCourseDetailsBinding;
import com.wintec.degreemap.viewmodel.CourseViewModel;

import static android.content.Context.MODE_PRIVATE;
import static com.wintec.degreemap.util.Constants.BUNDLE_COURSE_CODE;
import static com.wintec.degreemap.util.Constants.BUNDLE_PATHWAY;
import static com.wintec.degreemap.util.Constants.SHARED_PREFERENCES;

public class ManagerCourseDetailsFragment extends Fragment implements View.OnClickListener {
    private FragmentManagerCourseDetailsBinding binding;
    String selectedPathway;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_manager_course_details, container, false);
        View view = binding.getRoot();

        view.findViewById(R.id.btn_courseDetails_edit).setOnClickListener(this);
        view.findViewById(R.id.btn_courseDetails_delete).setOnClickListener(this);

        String courseCode = getArguments().getString(BUNDLE_COURSE_CODE);
        selectedPathway = getArguments().getString(BUNDLE_PATHWAY);

        CourseViewModel courseViewModel = new ViewModelProvider(this).get(CourseViewModel.class);
        courseViewModel.getCourseDetails(courseCode).observe(getViewLifecycleOwner(), new Observer<Course>() {
            @Override
            public void onChanged(Course course) {
                if (course != null) {
                    binding.setCourse(course);
                }
            }
        });

        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_courseDetails_edit:
                Bundle bundle = new Bundle();
                bundle.putString(BUNDLE_COURSE_CODE, binding.getCourse().getCode());
                bundle.putString(BUNDLE_PATHWAY, getArguments().getString(BUNDLE_PATHWAY));

                NavHostFragment.findNavController(this).navigate(R.id.action_managerCourseDetailsFragment_to_managerCourseFormFragment, bundle);
                break;
            case R.id.btn_courseDetails_delete:
                AlertDialog deleteDialog = new AlertDialog.Builder(getActivity())
                        .setTitle("Delete Course?")
                        .setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                deleteData();
                            }
                        })
                        .setNegativeButton("Cancel", null)
                        .create();
                deleteDialog.show();
                break;
        }
    }

    private void deleteData() {
        CourseViewModel courseViewModel = new ViewModelProvider(this).get(CourseViewModel.class);
        courseViewModel.deleteCourse(binding.getCourse().getCode());

        Bundle bundle = new Bundle();
        bundle.putString(BUNDLE_PATHWAY, selectedPathway);
        bundle.putString(BUNDLE_PATHWAY, getArguments().getString(BUNDLE_PATHWAY));

        NavHostFragment.findNavController(this).navigate(R.id.action_managerCourseDetailsFragment_to_managerCourseListFragment, bundle);
    }
}