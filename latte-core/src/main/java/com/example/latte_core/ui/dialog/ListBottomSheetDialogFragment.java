package com.example.latte_core.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.example.latte_core.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * 仿抖音评论列表
 */
public class ListBottomSheetDialogFragment extends BottomSheetDialogFragment {
    
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_FRAME, R.style.CustomBottomSheetDialogTheme);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.setCanceledOnTouchOutside(true);
        Window window = dialog.getWindow();
        WindowManager.LayoutParams params = window.getAttributes();
        // SOFT_INPUT_ADJUST_NOTHING:       不调整(输入法完全直接覆盖住,未开放此参数)
        // SOFT_INPUT_ADJUST_PAN:           把整个Layout顶上去露出获得焦点的EditText,不压缩多余空间
        // SOFT_INPUT_ADJUST_RESIZE:        整个Layout重新编排,重新分配多余空间
        // SOFT_INPUT_ADJUST_UNSPECIFIED:   系统自己根据内容自行选择上两种方式的一种执行(默认配置)
        window.setSoftInputMode(params.SOFT_INPUT_ADJUST_NOTHING);
        return dialog;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item_list_dialog, container, false);
        view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                getScreenHeight() / 2));
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final RecyclerView recyclerView = view.findViewById(R.id.list_comment);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(new ItemAdapter());
        view.findViewById(R.id.txt_comment).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InputDialog dialog = new InputDialog(getActivity());
                Window window = dialog.getWindow();
                WindowManager.LayoutParams params = window.getAttributes();
                window.setSoftInputMode(params.SOFT_INPUT_STATE_VISIBLE);
                dialog.show();
            }
        });
    }

    private class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.MyViewHolder> {

        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new MyViewHolder(LayoutInflater.from(getContext()).inflate(R.layout.item_multiple_text,
                    parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
            holder.setData();
        }

        @Override
        public int getItemCount() {
            return 10;
        }

        class MyViewHolder extends RecyclerView.ViewHolder {

            private AppCompatTextView text;

            public MyViewHolder(@NonNull View itemView) {
                super(itemView);
                text = itemView.findViewById(R.id.text_single);
            }

            public void setData() {
                text.setText("123");
            }
        }
    }

    public class InputDialog extends Dialog {

        public InputDialog(@NonNull Context context) {
            super(context);
            init(context);
        }

        private void init(Context context) {
            View view = LayoutInflater.from(context).inflate(R.layout.dialog_input, null);
            setContentView(view);
            Window window = getWindow();
            WindowManager.LayoutParams layoutParams = window.getAttributes();
            layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
            window.setAttributes(layoutParams);
            window.setGravity(Gravity.BOTTOM);
        }
    }

    private int getScreenHeight() {
        WindowManager windowManager = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        return windowManager.getDefaultDisplay().getHeight();
    }
}