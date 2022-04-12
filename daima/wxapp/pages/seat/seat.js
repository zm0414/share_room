// pages/seat/seat.js
var Util = require('../../utils/util.js');
const app = getApp();
Page({

  /**
   * 页面的初始数据
   */
  data: {
    seatList: [],
    date: ''
  },

  initDate: function() {
    var dates = [];
    for (var i = 0; i < 7; i++) {
        var date = {
            date: Util.formatAddDate(new Date(), i),
            week: Util.getWeek(new Date(), i),
            day: Util.formatMonthDay(new Date(), i)
        };
        dates.push(date);
    }
    console.log(dates)
    this.setData({
        dates: dates,
        date: Util.formatAddDate(new Date(), 0)
    })
},

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    this.initDate()
    var id = options.id;
    this.setData({
      roomId: id,
      storeId: options.storeId
    })
    this.getRoom(id)
    this.getSessions(this.data.date)
  },

  getRoom: function(id) {
    var that = this;
    wx.request({
      url: app.globalData.domain + '/api/room/detail',
      data: {
        id: id
      },
      success: function(res) {
        console.log(res.data.room.seatList)
        that.setData({
          room: res.data.room,
          price: res.data.room.price
        });
      }
    })
  },

  tabDate(e){
    var date = e.currentTarget.dataset.date;
    this.setData({
      date: date
    })
    this.getSessions(date)
  },

  getSessions(date){
    var that = this;
    wx.request({
      url: app.globalData.domain + '/api/sessions/list',
      data: {
        date: date,
        storeId: that.data.storeId,
        roomId: that.data.roomId
      },
      success: function(res) {
        that.setData({
          seatList: res.data.sessionsList
        });
      }
    })
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