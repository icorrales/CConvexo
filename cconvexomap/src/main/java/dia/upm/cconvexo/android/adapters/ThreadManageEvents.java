package dia.upm.cconvexo.android.adapters;

import android.view.MotionEvent;
import android.view.View;

/**
 * Created by ivan on 13/04/14.
 */
public class ThreadManageEvents extends OnSwipeTouchListener implements Runnable {

    View vista;

    public ThreadManageEvents ( View view)
    {
        super(view.getContext());

    }


    @Override
    public void run() {
        while ( true)
        {

        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return false;
    }
}
