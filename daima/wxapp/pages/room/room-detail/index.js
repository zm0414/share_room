//index.js
//获取应用实例
var app = getApp();
var Util = require('../../../utils/util.js');
Page({
  data: {
    room: {},
    evaluationList: [],
    play: false
  },

  initDate: function () {
    var dates = [];
    for (var i = 0; i < 7; i++) {
      var date = {
        date: Util.formatAddDate(new Date(), i),
        week: Util.getWeek(new Date(), i),
        day: Util.formatMonthDay(new Date(), i)
      };
      dates.push(date);
    }
    this.setData({
      dates: dates
    })
    console.log(dates)
  },

  onLoad: function(e) {
    if (e.distributor) {
      app.globalData.distributor = e.distributor;
    }
    var that = this;

    this.initDate();

    wx.request({
      url: app.globalData.domain + '/api/room/detail',
      data: {
        id: e.id
      },
      success: function(res) {
        var selectSizeTemp = "";
        that.data.room = res.data.room;
        that.setData({
          room: res.data.room,
        });
      }
    })

    //this.getEvaluation(e.id);

  },

  getEvaluation: function(roomId) {
    var that = this;
    wx.request({
      url: app.globalData.domain + '/api/room/evaluation/list',
      data: {
        bookId: roomId
      },
      success: function(res) {
        that.setData({
          evaluationList: res.data.evaluationList
        });
      }
    })
  },

  appoint: function(e) {
    if (!app.globalData.token) {
      wx.navigateTo({
        url: '/pages/login/index',
      })
      return;
    }
    var that = this;
    var id = this.data.room.id;
    var storeId = this.data.room.storeId;
    var date = e.currentTarget.dataset.date;
    wx.navigateTo({
      url: '/pages/hall/hall?id=' + id + "&storeId=" + storeId + '&date=' + date,
    })
  },

  telphone: function(e) {
    wx.makePhoneCall({
      phoneNumber: wx.getStorageSync('phone')
    })
  },

  onShareAppMessage: function() {
    var path = '/pages/room-detail/index?id=' + this.data.room.id;
    if (app.globalData.distributor) {
      path = path + "&distributor=" + app.globalData.distributor;
    }
    return {
      title: this.data.room.roomName,
      path: path,
      success: function(res) {
        // 转发成功
      },
      fail: function(res) {
        // 转发失败
      }
    }
  }
})

