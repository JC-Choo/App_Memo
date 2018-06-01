<<<<<<< HEAD:app/src/main/java/com/example/cnwlc/memo/Test/recycler2/RecyclerItemViewHolder2.java
package com.example.cnwlc.memo.Test.recycler2;
=======
package com.choo.application.memo.App.etc.recycler2;
>>>>>>> android:app/src/main/java/com/choo/application/memo/Test/recycler2/RecyclerItemViewHolder2.java

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.choo.application.memo.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecyclerItemViewHolder2 extends RecyclerView.ViewHolder {

    @BindView(R.id.RecyclerFragment2TextViewName)
    TextView tvRecyclerFragment2Name;
    @BindView(R.id.RecyclerFragment2CheckBox)
    CheckBox cbRecyclerFragment2;

    public RecyclerItemViewHolder2(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
