package fin.locator.droid.remote;

import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.Objects;

import static fin.locator.droid.remote.Status.ERROR;
import static fin.locator.droid.remote.Status.LOADING;
import static fin.locator.droid.remote.Status.SUCCESS;

public class Resource<T> {

    @NonNull
    private final Status status;

    @NonNull
    private final String message;

    @NonNull
    private final T data;


    public Resource(@NonNull Status status, @NonNull String message, @NonNull T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }



    public static <T> Resource<T> success(@Nullable T data){
        return new Resource<>(SUCCESS,null,data);
    }

    public static <T> Resource<T> error(String msg, @Nullable T data) {
        return new Resource<>(ERROR, msg, data);
    }

    public static <T> Resource<T> loading(@Nullable T data) {
        return new Resource<>(LOADING, null, data);
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Resource<?> resource = (Resource<?>) o;

        if (status != resource.status) {
            return false;
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            if (!Objects.equals(message, resource.message)) {
                return false;
            }
        } else {
            if (!message.equals(resource.message)) {
                return false;
            }
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            return Objects.equals(data, resource.data);
        } else {
            return data.equals(resource.data);
        }
    }




    @Override
    public int hashCode() {
        int result = status.hashCode();
        result = 31 * result + (message != null ? message.hashCode() : 0);
        result = 31 * result + (data != null ? data.hashCode() : 0);
        return result;
    }


    @Override
    public String toString() {
        return "Resource{" +
                "status=" + status +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
