package dia.upm.cconvexo.android.adapters;

import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;


/**
 * Created by ivan on 15/06/14.
 */
public class ConvexHullGestureListener extends GestureDetector.SimpleOnGestureListener {

    View vista = null;


    public ConvexHullGestureListener(View panelPuntos) {
        assert panelPuntos != null;
        this.vista = panelPuntos;


    }



    @Override
    public boolean onDown(MotionEvent e) {
        return true;
    }

    @Override
    public void onShowPress(MotionEvent e) {


    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        this.WriteEvent(e,"SingleTapUp");
        return true;
    }

    @Override
    public boolean onDoubleTap(MotionEvent e) {
        this.WriteEvent(e,"DoubleTapUp");
        return true;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        this.WriteEvent(e1,"Scroll "  + e1.toString() + " " + e2.toString());
        return true;
    }

    @Override
    public void onLongPress(MotionEvent e) {
        this.WriteEvent(e,"LongPress");

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        this.WriteEvent(e1,"Fling" + e1.toString() + " " + e2.toString());
        return true;
    }

    public boolean WriteEvent( MotionEvent event,String nameEvent) {

        assert event != null;
        assert nameEvent != null;
        Log.d(ConvexHullGestureListener.class.getName(), nameEvent);
        //Toast.makeText(vista.getContext(), nameEvent, Toast.LENGTH_SHORT).show();


        return true;
    }
}
