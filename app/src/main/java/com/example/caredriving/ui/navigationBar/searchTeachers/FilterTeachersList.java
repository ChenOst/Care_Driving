package com.example.caredriving.ui.navigationBar.searchTeachers;

import android.service.autofill.RegexValidator;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import com.example.caredriving.firebase.model.dataObject.TeacherObj;
import java.util.ArrayList;

public class FilterTeachersList {

    // taking care of all the filter options - 16 options
    public static void setNotificationInList(final View root, ArrayList<TeacherObj> teachers, ArrayList<TeacherObj> allTeachers, ArrayList<String> newLocations,
                                             ArrayList<String> newCarBrands, ArrayList<String> newGears, ArrayList<String> newPrices, RecyclerViewAdapter adapter) {
        String TAG = "FilterTeachersList";
        Log.d(TAG, "setNotificationInList: called.");
        // 0 0 0 0
        if (newLocations.isEmpty() && newCarBrands.isEmpty() && newGears.isEmpty() && newPrices.isEmpty()) {
            teachers.clear();
            teachers.addAll(allTeachers);
            adapter.notifyDataSetChanged();
        }
        // 0 0 0 1
        else if (newLocations.isEmpty() && newCarBrands.isEmpty() && newGears.isEmpty() && !newPrices.isEmpty()) {
            teachers.clear();
            for (TeacherObj teacher : allTeachers) {
                for (String price : newPrices) {
                    String[] parts = price.split("\\–");
                    int lower = Integer.parseInt(parts[0]);
                    int upper = Integer.parseInt(parts[1]);
                    if (lower <= Integer.parseInt(teacher.getLessonPrice()) && upper >= Integer.parseInt(teacher.getLessonPrice())) {
                        teachers.add(teacher);
                    }
                }
            }
            adapter.notifyDataSetChanged();
        }
        // 0 0 1 0
        else if (newLocations.isEmpty() && newCarBrands.isEmpty() && !newGears.isEmpty() && newPrices.isEmpty()) {
            teachers.clear();
            for (TeacherObj teacher : allTeachers) {
                if (newGears.contains(teacher.getTransmission())) {
                    teachers.add(teacher);
                }
            }
            adapter.notifyDataSetChanged();
        }
        // 0 0 1 1
        else if (newLocations.isEmpty() && newCarBrands.isEmpty() && !newGears.isEmpty() && !newPrices.isEmpty()) {
            teachers.clear();
            for (TeacherObj teacher : allTeachers) {
                if (newGears.contains(teacher.getTransmission())) {
                    for (String price : newPrices) {
                        String[] parts = price.split("\\–");
                        int lower = Integer.parseInt(parts[0]);
                        int upper = Integer.parseInt(parts[1]);
                        if (lower <= Integer.parseInt(teacher.getLessonPrice()) && upper >= Integer.parseInt(teacher.getLessonPrice())) {
                            teachers.add(teacher);
                        }
                    }
                }
            }
            adapter.notifyDataSetChanged();
        }
        // 0 1 0 0
        else if (newLocations.isEmpty() && !newCarBrands.isEmpty() && newGears.isEmpty() && newPrices.isEmpty()) {
            teachers.clear();
            for (TeacherObj teacher : allTeachers) {
                if (newCarBrands.contains(teacher.getCarType())) {
                    teachers.add(teacher);
                }
            }
            adapter.notifyDataSetChanged();
        }
        // 0 1 0 1
        else if (newLocations.isEmpty() && !newCarBrands.isEmpty() && newGears.isEmpty() && !newPrices.isEmpty()) {
            teachers.clear();
            for (TeacherObj teacher : allTeachers) {
                if (newCarBrands.contains(teacher.getCarType())) {
                    for (String price : newPrices) {
                        String[] parts = price.split("\\–");
                        int lower = Integer.parseInt(parts[0]);
                        int upper = Integer.parseInt(parts[1]);
                        if (lower <= Integer.parseInt(teacher.getLessonPrice()) && upper >= Integer.parseInt(teacher.getLessonPrice())) {
                            teachers.add(teacher);
                        }
                    }
                }
            }
            adapter.notifyDataSetChanged();
        }
        // 0 1 1 0
        else if (newLocations.isEmpty() && !newCarBrands.isEmpty() && !newGears.isEmpty() && newPrices.isEmpty()) {
            teachers.clear();
            for (TeacherObj teacher : allTeachers) {
                if (newCarBrands.contains(teacher.getCarType()) && newGears.contains(teacher.getTransmission())) {
                    teachers.add(teacher);
                }
            }
            adapter.notifyDataSetChanged();
        }
        // 0 1 1 1
        else if (newLocations.isEmpty() && !newCarBrands.isEmpty() && !newGears.isEmpty() && !newPrices.isEmpty()) {
            teachers.clear();
            for (TeacherObj teacher : allTeachers) {
                if (newCarBrands.contains(teacher.getCarType()) && newGears.contains(teacher.getTransmission())) {
                    for (String price : newPrices) {
                        String[] parts = price.split("\\–");
                        int lower = Integer.parseInt(parts[0]);
                        int upper = Integer.parseInt(parts[1]);
                        if (lower <= Integer.parseInt(teacher.getLessonPrice()) && upper >= Integer.parseInt(teacher.getLessonPrice())) {
                            teachers.add(teacher);
                        }
                    }
                }
            }
            adapter.notifyDataSetChanged();
        }
        // 1 0 0 0
        else if (!newLocations.isEmpty() && newCarBrands.isEmpty() && newGears.isEmpty() && newPrices.isEmpty()) {
            teachers.clear();
            for (TeacherObj teacher : allTeachers) {
                if (newLocations.contains(teacher.getCity())) {
                    teachers.add(teacher);
                }
            }
            adapter.notifyDataSetChanged();
        }
        // 1 0 0 1
        else if (!newLocations.isEmpty() && newCarBrands.isEmpty() && newGears.isEmpty() && !newPrices.isEmpty()) {
            teachers.clear();
            for (TeacherObj teacher : allTeachers) {
                if (newLocations.contains(teacher.getCity())) {
                    for (String price : newPrices) {
                        String[] parts = price.split("\\–");
                        int lower = Integer.parseInt(parts[0]);
                        int upper = Integer.parseInt(parts[1]);
                        if (lower <= Integer.parseInt(teacher.getLessonPrice()) && upper >= Integer.parseInt(teacher.getLessonPrice())) {
                            teachers.add(teacher);
                        }
                    }
                }
            }
            adapter.notifyDataSetChanged();
        }
        // 1 0 1 0
        else if (!newLocations.isEmpty() && newCarBrands.isEmpty() && !newGears.isEmpty() && newPrices.isEmpty()) {
            teachers.clear();
            for (TeacherObj teacher : allTeachers) {
                if (newLocations.contains(teacher.getCity()) && newGears.contains(teacher.getTransmission())) {
                    teachers.add(teacher);
                }
            }
            adapter.notifyDataSetChanged();
        }
        // 1 0 1 1
        else if (!newLocations.isEmpty() && newCarBrands.isEmpty() && !newGears.isEmpty() && !newPrices.isEmpty()) {
            teachers.clear();
            for (TeacherObj teacher : allTeachers) {
                if (newLocations.contains(teacher.getCity()) && newGears.contains(teacher.getTransmission())) {
                    for (String price : newPrices) {
                        String[] parts = price.split("\\–");
                        int lower = Integer.parseInt(parts[0]);
                        int upper = Integer.parseInt(parts[1]);
                        if (lower <= Integer.parseInt(teacher.getLessonPrice()) && upper >= Integer.parseInt(teacher.getLessonPrice())) {
                            teachers.add(teacher);
                        }
                    }
                }
            }
            adapter.notifyDataSetChanged();
        }
        // 1 1 0 0
        else if (!newLocations.isEmpty() && !newCarBrands.isEmpty() && newGears.isEmpty() && newPrices.isEmpty()) {
            teachers.clear();
            for (TeacherObj teacher : allTeachers) {
                if (newLocations.contains(teacher.getCity()) && newCarBrands.contains(teacher.getCarType())) {
                    teachers.add(teacher);
                }
            }
            adapter.notifyDataSetChanged();
        }
        // 1 1 0 1
        else if (!newLocations.isEmpty() && !newCarBrands.isEmpty() && newGears.isEmpty() && !newPrices.isEmpty()) {
            teachers.clear();
            for (TeacherObj teacher : allTeachers) {
                if (newLocations.contains(teacher.getCity()) && newCarBrands.contains(teacher.getCarType())) {
                    for (String price : newPrices) {
                        String[] parts = price.split("\\–");
                        int lower = Integer.parseInt(parts[0]);
                        int upper = Integer.parseInt(parts[1]);
                        if (lower <= Integer.parseInt(teacher.getLessonPrice()) && upper >= Integer.parseInt(teacher.getLessonPrice())) {
                            teachers.add(teacher);
                        }
                    }
                }
            }
            adapter.notifyDataSetChanged();
        }
        // 1 1 1 0
        else if (!newLocations.isEmpty() && !newCarBrands.isEmpty() && !newGears.isEmpty() && newPrices.isEmpty()) {
            teachers.clear();
            for (TeacherObj teacher : allTeachers) {
                if (newLocations.contains(teacher.getCity()) && newCarBrands.contains(teacher.getCarType())
                        && newGears.contains(teacher.getTransmission())) {
                    teachers.add(teacher);
                }
            }
            adapter.notifyDataSetChanged();
        }
        // 1 1 1 1
        else if (!newLocations.isEmpty() && !newCarBrands.isEmpty() && !newGears.isEmpty() && !newPrices.isEmpty()) {
            teachers.clear();
            for (TeacherObj teacher : allTeachers) {
                if (newLocations.contains(teacher.getCity()) && newCarBrands.contains(teacher.getCarType())
                        && newGears.contains(teacher.getTransmission())) {
                    for (String price : newPrices) {
                        String[] parts = price.split("\\–");
                        int lower = Integer.parseInt(parts[0]);
                        int upper = Integer.parseInt(parts[1]);
                        if (lower <= Integer.parseInt(teacher.getLessonPrice()) && upper >= Integer.parseInt(teacher.getLessonPrice())) {
                            teachers.add(teacher);
                        }
                    }
                }
            }
            adapter.notifyDataSetChanged();

        } else {
            Toast.makeText(root.getContext(), "Something went wrong...", Toast.LENGTH_SHORT).show();
        }
    }
}
