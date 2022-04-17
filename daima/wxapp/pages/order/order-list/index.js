var WxPay = require('../../../utils/pay.js')
var sliderWidth = 57.6; // 需要设置slider的宽度，用于计算中间位置
var app = getApp()
Page({
  data: {
    tabs: ["全部", "待付款", "使用中", "已完成"],
    activeIndex: 0,
    statuses: ''
  },

  tabClick: function (e) {
    this.setData({
      activeIndex: e.currentTarget.id,
      sliderOffset: e.currentTarget.offsetLeft,
      statuses: this.getStatus(e.currentTarget.id)
    });
    this.getOrderList();
  },

  toPay: function(e){
    var id = e.currentTarget.dataset.id;
    wx.navigateTo({
      url: '/pages/order/order-pay/index?id='+id,
    })
    return;
    var that = this;
    var totalAmount = e.currentTarget.dataset.totalamount;
    var orderNum = e.currentTarget.dataset.ordernum;
    WxPay.wxpay(app, totalAmount, orderNum, '商品购买', function (code) {
      // 
      if (code == 0) {
        that.getOrderList();
      }
    });
  },

  followUp: function (e) {
    var orderId = e.currentTarget.dataset.id;
    var that = this;
    wx.showLoading();
    wx.request({
      url: app.globalData.domain + '/api/bms/order/followUp',
      data: {
        token: app.globalData.token,
        storeId: app.globalData.storeId,
        orderId: orderId
      },
      success: (res) => {
        wx.hideLoading();
        if (res.data.code == 0) {
          that.getOrderList();
        }
      }
    })
  },

  getStatus: function (activeIndex) {
    if (activeIndex == 0) {
      return '';
    } else if (activeIndex == 1) {
      return '1';
    } else if (activeIndex == 2) {
      return '2';
    } else if (activeIndex == 3) {
      return '3';
    } else if (activeIndex == 4) {
      return '4';
    }
  },

  orderDetail: function (e) {
    var orderId = e.currentTarget.dataset.id;
    wx.navigateTo({
      url: "/pages/order-details/index?id=" + orderId
    })
  },

  onLoad: function (options) {
    // 生命周期函数--监听页面加载
    var that = this;

    wx.getSystemInfo({
      success: function (res) {
        that.setData({
          sliderLeft: (res.windowWidth / that.data.tabs.length - sliderWidth) / 2,
          sliderOffset: res.windowWidth / that.data.tabs.length * that.data.activeIndex
        });
      }
    });
  },
// 展示订单列表方法
  getOrderList: function () {
    var that = this;
    wx.request({
      // 请求路径
      url: app.globalData.domain + '/api/order/list',
      data: {
        // 数据：token  订单状态 支付状态 （0.全部 1.待支付 2.使用中 3.已完成）
        token: app.globalData.token,
        storeId: app.globalData.storeId,
        status: that.data.statuses,
        orderType: 1
      },
      success: (res) => {
        wx.hideLoading();
        if (res.data.code == 0) {
          that.setData({
            orderList: res.data.orderList
          });
        }
      }
    })
  },

  cancel: function (e) {
    var that = this;
    var totalAmount = e.currentTarget.dataset.totalamount;
    var orderNum = e.currentTarget.dataset.ordernum;
    var id = e.currentTarget.dataset.id;
    wx.request({
      url: app.globalData.domain + '/api/order/cancel',
      header:{
        token: app.globalData.token
      },
      data: {id: id},
      success: (res) => {
        that.getOrderList();
      }
    })
  },

  confirm: function(e){
    var that = this;
    var orderNum = e.currentTarget.dataset.ordernum;
    var id = e.currentTarget.dataset.id;
    wx.request({
      url: app.globalData.domain + '/api/order/confirm',
      header: {
        token: app.globalData.token
      },
      data: { id: id },
      success: (res) => {
        that.getOrderList();
      }
    })
  },

  evaluation: function (e) {
    console.log(e)
    var orderId = e.currentTarget.dataset.id;
    var goodsId = e.currentTarget.dataset.goodsid;
    wx.navigateTo({
      url: '/pages/shop/evaluation/index?orderId=' + orderId + "&goodsId=" + goodsId,
    })
  },

  onReady: function () {
    // 生命周期函数--监听页面初次渲染完成

  },

  onShow: function () {
    this.getOrderList();
  },
  onHide: function () {
    // 生命周期函数--监听页面隐藏

  },
  onUnload: function () {
    // 生命周期函数--监听页面卸载

  },
  onPullDownRefresh: function () {
    // 页面相关事件处理函数--监听用户下拉动作

  },
  onReachBottom: function () {
    // 页面上拉触底事件的处理函数

  },
  logistics: function(e){
    var id = e.currentTarget.dataset.id;
    wx.navigateTo({
      url: '/pages/shop/logistics/index?id=' + id,
    })
  }
})