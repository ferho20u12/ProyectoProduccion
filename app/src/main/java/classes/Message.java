package classes;

import android.content.Context;
import android.widget.Toast;

public class Message {
    private Toast toast;
    private Context context;
    public  Message(Context context){
        this.context = context;
        toast = Toast.makeText(this.context,"",Toast.LENGTH_SHORT);
    }
    public void ShowNewMessage(String str){
        toast.cancel();
        toast = Toast.makeText(this.context,str,Toast.LENGTH_SHORT);
        toast.show();
    }
}
