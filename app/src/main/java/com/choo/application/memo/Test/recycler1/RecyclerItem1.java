<<<<<<< HEAD:app/src/main/java/com/example/cnwlc/memo/Test/recycler1/RecyclerItem1.java
package com.example.cnwlc.memo.Test.recycler1;
=======
package com.choo.application.memo.App.etc.recycler1;
>>>>>>> android:app/src/main/java/com/choo/application/memo/Test/recycler1/RecyclerItem1.java

public class RecyclerItem1 {
    private String name;
    private boolean isChecked;

    public RecyclerItem1(String name, boolean isChecked) {
        this.name = name;
        this.isChecked = isChecked;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }
}
