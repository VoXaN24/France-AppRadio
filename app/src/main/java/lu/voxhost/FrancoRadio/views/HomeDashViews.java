package lu.voxhost.FrancoRadio.views;

import lu.voxhost.FrancoRadio.models.Category;
import lu.voxhost.FrancoRadio.models.Channels;

import java.util.List;

public interface HomeDashViews {
    void showLoading();
    void hideLoading();
    void onErrorLoading(String message);
    void setSlider(List<Channels> dataList);
    void setRecentCategory(List<Category> dataList);
    void setMostViewed(List<Channels> dataList);
}
