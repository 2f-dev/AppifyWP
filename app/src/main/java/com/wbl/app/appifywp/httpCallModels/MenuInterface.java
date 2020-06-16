package com.wbl.app.appifywp.httpCallModels;

import com.wbl.app.appifywp.models.WpMenuItem;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface MenuInterface {
    @GET("/wp-json/menus/v1/menus/{menu_name}")
    Call<WpMenuItem> getMenu(@Path("menu_name") String menuName);
}
