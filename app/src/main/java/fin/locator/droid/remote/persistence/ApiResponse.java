package fin.locator.droid.remote.persistence;

import android.util.Log;

import androidx.annotation.Nullable;

import java.util.regex.Pattern;

import retrofit2.Response;

public class ApiResponse<T>{

    private static final String TAG = "ApiResponse";

    public final int code;

    @Nullable
    public final T body;


    @Nullable
    public final String errorMessage;



    public ApiResponse(Throwable throwable){
        code = 500;
        errorMessage = throwable.getMessage();
        body = null;
    }


    public ApiResponse(Response<T> response){

        code = response.code();

        //if response is successful
         if (response.isSuccessful()){
             body = response.body();
             errorMessage = null;
         }

         //if response is not successful
         else {
             String message = null;

             // if the error body ain't null
             if (response.errorBody() != null){
                 try {
                     message = response.errorBody().toString();
                 }
                 catch (Exception ignored){
                     Log.e(TAG, "ApiResponse: ",ignored );
                 }

             }
              //else error body is null
             if (message == null || message.trim().length() == 0) {
                 message = response.message();
             }

             errorMessage = message;
             body = null;

         }


    }



    public boolean isSuccessful() {
        return code >= 200 && code < 300;
    }



}
