package lu.voxhost.FrancoRadio.views;

import lu.voxhost.FrancoRadio.models.Channels;

import java.util.List;

public interface ChannelViews {
    void showLoading();
    void hideLoading();
    void onErrorLoading(String message);
    void setChannels(List<Channels> dataList);
}
