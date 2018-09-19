package app.creatingminds.ecoscan.ui.main;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.Calendar;

import app.creatingminds.ecoscan.R;

/**
 * Created by tom on 9/17/18.
 */

public class InputDialogFragment extends DialogFragment implements View.OnClickListener {

    private EditText edtFoodName;
    private DatePicker datePicker;
    private Button btnOK;
    private Button btnCancel;

    private OnPositiveClickListener listener;

    public InputDialogFragment() {
        // Empty constructor is required for DialogFragment
        // Make sure not to add arguments to the constructor
        // Use `newInstance` instead as shown below
    }

    public static InputDialogFragment newInstance() {
        InputDialogFragment frag = new InputDialogFragment();
        Bundle args = new Bundle();
        frag.setArguments(args);
        return frag;
    }

    public void setOnPositiveClickListener(OnPositiveClickListener listener) {
        this.listener = listener;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dialog_fragment_input, container);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // Get field from view
        edtFoodName = view.findViewById(R.id.edt_food_name);
        datePicker = view.findViewById(R.id.date_picker);
        btnOK = view.findViewById(R.id.btn_ok);
        btnCancel = view.findViewById(R.id.btn_cancel);

        btnOK.setOnClickListener(this);
        btnCancel.setOnClickListener(this);

        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_ok:
                if (listener != null) {
                    Calendar calendar = Calendar.getInstance();
                    calendar.set(datePicker.getYear(), datePicker.getMonth(), datePicker.getDayOfMonth());
                    String foodName = edtFoodName.getText().toString();
                    clearInput();
                    listener.onClick(this, foodName, calendar.getTimeInMillis());
                }
                break;

            case R.id.btn_cancel:
                clearInput();
                dismiss();
                break;

            default:
                break;
        }
    }

    private void clearInput() {
        edtFoodName.setText("");
    }

    public interface OnPositiveClickListener {
        void onClick(DialogFragment dialog, String foodName, long timestamp);
    }
}
