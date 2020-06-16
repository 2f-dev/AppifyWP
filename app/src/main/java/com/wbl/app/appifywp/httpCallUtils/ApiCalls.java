package com.wbl.app.appifywp.httpCallUtils;

import com.wbl.app.appifywp.httpCallModels.MenuInterface;
import com.wbl.app.appifywp.models.WpMenuItem;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiCalls {
    public static WpMenuItem callGetMenu(String url, String menuName) throws InterruptedException, ExecutionException {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Callable<WpMenuItem> callable = () -> {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            MenuInterface service = retrofit.create(MenuInterface.class);

            Call<WpMenuItem> callSync = service.getMenu(menuName);

            try {
                Response<WpMenuItem> response = callSync.execute();
                WpMenuItem apiResponse = response.body();
                return apiResponse;
            } catch (Exception ex) {
                throw ex;
            }
        };
        Future<WpMenuItem> future = executor.submit(callable);
        WpMenuItem res = future.get();
        executor.shutdown();

        return res;
    }
}
