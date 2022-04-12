// pages/balance/index.js
const app = getApp();
var WxPay = require('../../../utils/pay.js')
Page({

  /**
   * 页面的初始数据
   */
  data: {
    balance: 0,
    rechargeList: [{
        amount: 10,
        amountGive: 0
      },
      {
        amount: 100,
        amountGive: 10
      },
      {
        amount: 200,
        amountGive: 20
      },
      {
        amount: 300,
        amountGive: 30
      },
      {
        amount: 400,
        amountGive: 30
      },
      {
        amount: 500,
        amountGive: 50
      }
    ]
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function(options) {

  },

  withdraw(){
    wx.navigateTo({
      url: '/pages/my/withdraw/index',
    })
  },

  getRecharge: function() {
    var that = this;
    wx.request({
      url: app.globalData.domain + '/app/recharge/list',
      success: function(res) {
        if (res.data.code == 0) {
          that.setData({
            rechargeList: res.data.rechargeList
          })
        }
      }
    })
  },

  buy: function() {
    var that = this;
    var amount = that.data.amount;
    var amountGive = that.data.amountGive;
    if (!amount) {
      wx.showToast({
        title: '请选择充值金额',
      })
      return;
    }

    wx.showLoading();
    wx.request({
      url: app.globalData.domain + '/api/member/recharge',
      header: {
        token: app.globalData.token
      },
      method: "GET",
      data: {
        amount: amount + amountGive
      },
      success: (res) => {
        if (res.data.code == 0) {
          wx.showToast({
            title: '充值成功'
          })
          that.getBalance();
        }
      },
      complete: (res) => {
        wx.hideLoading();
      }
    })

  },

  buyOther: function() {
    wx.navigateTo({
      url: '/pages/recharge-other/index',
    })
  },

  buyCard: function() {
    wx.navigateTo({
      url: '/pages/recharge-volume/index',
    })
  },

  tabAmount: function(e) {
    var amount = e.currentTarget.dataset.amount;
    var amountGive = e.currentTarget.dataset.amountgive;
    var activeIndex = e.currentTarget.dataset.index;
    var totalAmount = amount + amountGive;
    var valid = e.currentTarget.dataset.valid;
    this.setData({
      amount: amount,
      amountGive: amountGive,
      totalAmount: totalAmount,
      activeIndex: activeIndex
    })
    console.log(e);
  },

  getBalance: function () {
    var that = this;
    wx.request({
      url: app.globalData.domain + '/api/member/info',
      data: {
        token: app.globalData.token
      },
      success: function (res) {
        that.setData({
          balance: res.data.member.balance
        });
      }
    })
  },

  rechargeRecord: function() {
    wx.navigateTo({
      url: '/pages/recharge-record/index',
    })
  },

  setPassword: function(e) {
    wx.navigateTo({
      url: '/pages/password/index',
    })
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
    this.getBalance();
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

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function() {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function() {

  }
})