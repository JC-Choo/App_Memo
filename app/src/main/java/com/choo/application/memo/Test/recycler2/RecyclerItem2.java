<<<<<<< HEAD:app/src/main/java/com/example/cnwlc/memo/Test/recycler2/RecyclerItem2.java
package com.example.cnwlc.memo.Test.recycler2;
=======
package com.choo.application.memo.App.etc.recycler2;
>>>>>>> android:app/src/main/java/com/choo/application/memo/Test/recycler2/RecyclerItem2.java

public class RecyclerItem2 {
    private String name;
    private boolean isChecked;

    public RecyclerItem2(String name, boolean isChecked) {
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
