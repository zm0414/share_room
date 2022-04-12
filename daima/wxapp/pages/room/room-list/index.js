// pages/room/room-list/index.js
const app = getApp()

Page({

  /**
   * 页面的初始数据
   */
  data: {
    roomList: [],
    isLoad: false,
    page: 1,
    pageSize: 10,
    categoryId: "",
    inputVal: ""
  },

  showInput: function() {
    this.setData({
      inputShowed: true
    });
  },

  hideInput: function() {
    this.setData({
      inputVal: "",
      inputShowed: false
    });
    this.getGoodsList();
  },

  clearInput: function() {
    this.setData({
      inputVal: ""
    });
    this.getGoodsList();
  },

  inputTyping: function(e) {
    this.setData({
      inputVal: e.detail.value
    });
    this.getGoodsList();
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function(options) {
    if (options.categoryId) {
      this.setData({
        categoryId: options.categoryId
      });
    }
    this.getGoodsList();
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function() {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function() {

  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function() {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function() {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function() {
    wx.showNavigationBarLoading()
    this.setData({
      page: 1
    });
    this.getGoodsList();
    setTimeout(function() {
      wx.hideNavigationBarLoading(); //完成停止加载
      wx.stopPullDownRefresh(); //停止下拉刷新
    }, 1000);
  },

  loadMore: function () {
    console.log("load more")
    var that = this;
    var isLoad = this.data.isLoad;
    console.log(isLoad)
    if (!isLoad) {
      this.setData({
        page: that.data.page + 1
      });
      this.getGoodsList();
    }
  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function() {
    this.loadMore();
  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function() {

  },

  getGoodsList: function() {
    var that = this;
    that.setData({
      isLoad: true
    });
    wx.request({
      url: app.globalData.domain + '/api/room/list',
      data: {
        page: that.data.page,
        limit: that.data.pageSize,
        roomName: that.data.inputVal
      },
      success: function(res) {
        if (res.data.code != 0) {
          that.setData({
            isLoad: false
          });
          return;
        }
        if (that.data.page == 1) {
          that.setData({
            roomList: []
          });
        }
        if (res.data.roomList.length == 0) {
          that.setData({
            isLoad: true
          });
          return;
        }
        var room = that.data.roomList;
        for (var i = 0; i < res.data.roomList.length; i++) {
          room.push(res.data.roomList[i]);
        }
        that.setData({
          roomList: room,
          isLoad: false
        });
      }
    })
  }
  
})