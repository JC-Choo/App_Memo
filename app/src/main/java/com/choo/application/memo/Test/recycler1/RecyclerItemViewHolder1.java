<<<<<<< HEAD:app/src/main/java/com/example/cnwlc/memo/Test/recycler1/RecyclerItemViewHolder1.java
package com.example.cnwlc.memo.Test.recycler1;
=======
package com.choo.application.memo.App.etc.recycler1;
>>>>>>> android:app/src/main/java/com/choo/application/memo/Test/recycler1/RecyclerItemViewHolder1.java

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.choo.application.memo.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecyclerItemViewHolder1 extends RecyclerView.ViewHolder {

    @BindView(R.id.RecyclerFragment1TextViewName)
    TextView tvRecyclerFragment1Name;
    @BindView(R.id.RecyclerFragment1CheckBox)
    CheckBox cbRecyclerFragment1;

    public RecyclerItemViewHolder1(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
