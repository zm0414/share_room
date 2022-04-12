//index.js
//获取应用实例
const app = getApp()

Page({
  data: {
    autoplay: true,
    interval: 3000,
    duration: 1000,
    bannerList: [],
    categoryList: [],
    roomList: [],
    isLoad: false,
    page: 1,
    pageSize: 10,
  },

  onLoad: function() {
    this.getAdvert();
    this.getCategory();
    this.getGoods();
  },
  tel(){
    wx.makePhoneCall({
      phoneNumber: '15358267279' 
    })
  },

  onShow: function(){
    
  },

  showInput: function () {
    wx.navigateTo({
      url: '/pages/room/room-list/index',
    })
  },

  //广告
  getAdvert: function() {
    var that = this;
    wx.request({
      url: app.globalData.domain + '/api/advert/list',
      data: {
        position: 'shop'
      },
      success: function(res) {
        that.setData({
          bannerList: res.data.advertList
        });
      }
    })
  },

  getCategory: function() {
    var that = this;
    wx.request({
      url: app.globalData.domain + '/api/category/list',
      data: {
      },
      success: function(res) {
        that.setData({
          categoryList: res.data.categoryList
        });
      }
    })
  },

  getGoods: function() {
    var that = this;
    wx.request({
      url: app.globalData.domain + '/api/room/list',
      data: {
        page: that.data.page,
        limit: that.data.pageSize
      },
      success: function(res) {
        if (that.data.page == 1) {
          that.setData({
            roomList: []
          });
        }
        if (res.data.code != 0) {
          that.setData({
            isLoad: false
          });
          return;
        }
        if (res.data.roomList.length == 0) {
          that.setData({
            isLoad: true
          });
          return;
        }
        var goods = that.data.roomList;
        for (var i = 0; i < res.data.roomList.length; i++) {
          goods.push(res.data.roomList[i]);
        }
        that.setData({
          roomList: goods,
          isLoad: false
        });
      }
    })
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
      this.getGoods();
    }
  },

  onPullDownRefresh: function() {
    this.setData({
      page: 1
    });
    wx.showNavigationBarLoading()
    this.getAdvert();
    this.getCategory();
    this.getGoods();
    setTimeout(function() {
      wx.hideNavigationBarLoading() //完成停止加载
      wx.stopPullDownRefresh() //停止下拉刷新
    }, 1000);
  },

  onShareAppMessage: function() {
    var path = '/pages/index/index';
    if (app.globalData.distributor) {
      path = path + "?distributor=" + app.globalData.distributor;
    }
    return {
      title: wx.getStorageSync('storeName'),
      path: path,
      success: function(res) {
        // 转发成功
      },
      fail: function(res) {
        // 转发失败
      }
    }
  },
  /**
  * 页面上拉触底事件的处理函数
  */
  onReachBottom: function () {
    this.loadMore();
  }
})