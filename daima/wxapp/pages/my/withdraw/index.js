// pages/my/withdraw/index.js
const app = getApp();
Page({

  /**
   * 页面的初始数据
   */
  data: {

  },

  bindSave(e){
    var that = this;
    var amount = e.detail.value.amount;
    if (!amount) {
      wx.showToast({
        title: '请填写金额',
      })
      return;
    }

    wx.showLoading();
    wx.request({
      url: app.globalData.domain + '/api/member/withdraw',
      header: {
        token: app.globalData.token
      },
      method: "GET",
      data: {
        amount: amount
      },
      success: (res) => {
        if (res.data.code == 0) {
          wx.showToast({
            title: '充值成功'
          })
          wx.navigateBack({
            delta: 0,
          })
        }else{
          wx.showToast({
            title: res.data.msg,
            icon: 'none'
          })
        }
      },
      complete: (res) => {
        wx.hideLoading();
      }
    })
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {

  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {

  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function () {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function () {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {

  }
})