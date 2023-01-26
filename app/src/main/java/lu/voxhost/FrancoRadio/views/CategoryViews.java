package lu.voxhost.FrancoRadio.views;

import lu.voxhost.FrancoRadio.models.Category;

import java.util.List;

public interface CategoryViews {
    void showLoading();
    void hideLoading();
    void onErrorLoading(String message);
    void setCategory(List<Category> dataList);
}
