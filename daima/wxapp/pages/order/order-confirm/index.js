// pages/confirm-order/index.js
const app = getApp();
var WxPay = require('../../../utils/pay.js')
Page({

  /**
   * 页面的初始数据
   */
  data: {
    distribution: [{
        name: '0',
        value: '自取',
        checked: 'true'
      },
      {
        name: '1',
        value: '邮寄'
      }
    ],
    distributionIndex: 0,
    totalAmount: 0
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function(options) {
    
    var roomList = wx.getStorageSync('roomList');
    var seatList = wx.getStorageSync('seatList');
    var totalAmount = 0;
    for (var i = 0; i < roomList.length; i++) {
      totalAmount += roomList[i].price;
    }

    this.setData({
      roomList: roomList,
      seatList: seatList,
      totalAmount: totalAmount
    })
  },

  getContent: function() {
    var that = this;
    wx.request({
      url: app.globalData.domain + '/app/cms/content',
      data: {
        type: 3
      },
      success: function(res) {
        if (res.data.code == 0) {
          that.setData({
            content: res.data.content
          })
        }
      }
    })
  },

  getGoods: function(id) {
    var that = this;
    wx.request({
      url: app.globalData.domain + '/app/goods/detail',
      data: {
        id: id
      },
      success: function(res) {
        that.setData({
          goods: res.data.goods,
        });
      }
    })
  },

  confirmOrder: function(e) {
    console.log(e);
    var remark = e.detail.value.remark;
    var that = this;
    var loginToken = app.globalData.token // 用户登录 token
    var orderStatus = 1;
    if (this.data.distributionIndex == 1) { //快递
      if (!this.data.orderShipment) {
        wx.showToast({
          title: '请选择收货地址',
        })
        return;
      }
      orderStatus = 0;
    }

    var orderRoomList = [];
    var roomList = this.data.roomList;
    for (var i = 0; i < roomList.length; i++) {
      var ordergoods = {
        roomId: roomList[i].id,
        roomName: roomList[i].roomName,
        picUrl: roomList[i].picUrl,
        price: roomList[i].price,
        num: 1
      };
      orderRoomList.push(ordergoods)
    }

    var orderSeatList = [];
    var seatList = this.data.seatList;
    for (var i = 0; i < seatList.length; i++) {
      var orderSeat = {
        sessionsId: seatList[i].id,
        seatName: seatList[i].seatInfo,
        roomId: roomList[0].id
      };
      orderSeatList.push(orderSeat)
    }
    
    var order = {
      orderRoomList: orderRoomList,
      storeId: app.globalData.storeId,
      orderStatus: orderStatus,
      orderShipment: that.data.orderShipment,
      distributionType: that.data.distribution[that.data.distributionIndex].name,
      totalAmount: that.data.totalAmount,
      orderType: 1,
      remark: remark,
      orderSeatList: orderSeatList
    };
    wx.showLoading({})

    wx.request({
      url: app.globalData.domain + '/api/order/create',
      method: 'POST',
      header: {
        token: loginToken
      },
      data: order,
      success: (res) => {
        if (res.data.code != 0) {
          wx.showModal({
            title: '提示',
            content: res.data.msg,
            showCancel: false,
            success: function(e) {

            }
          })
          return;
        }
        //模拟支付
        wx.navigateTo({
          url: '/pages/order/order-pay/index?id=' + res.data.id,
        })

        return;

        if (this.data.distributionIndex == 1) {
          WxPay.wxpay(app, res.data.totalAmount, res.data.orderNum, '商品购买', function(code) {
            // 下单成功，跳转到订单管理界面
            if (code == 0) {
              wx.navigateTo({
                url: "/pages/shop/order/order-list/index"
              });
            }
          });
        } else {
          wx.navigateTo({
            url: "/pages/shop/order/order-list/index"
          });
        }

      },
      complete: function(res) {
        wx.hideLoading();
      }
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