// pages/classify/index.js

const app = getApp()

Page({

  /**
   * 页面的初始数据
   */
  data: {
    activeIndex: 0,
    categoryList: [],
    secondaryCategoryList: []
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    this.getCategory(0);
  },

  tabClick: function (e) {
    this.setData({
      activeIndex: e.currentTarget.id
    });
    this.getGoodsList(e.currentTarget.dataset.id);
  },

  getCategory: function(parentId){
    var that = this;
    wx.request({
      url: app.globalData.domain + '/api/category/list',
      data: { },
      success: function (res) {
        if (res.data.code == 0) {
          that.setData({
            categoryList: res.data.categoryList
          });
          that.getGoodsList(res.data.categoryList[0].id)
        }
      }
    })
  },

  getGoodsList: function (categoryId) {
    var that = this;
    wx.request({
      url: app.globalData.domain + '/api/room/list',
      data: {
        categoryId: categoryId
      },
      success: function (res) {
        that.setData({
          goodsList: res.data.goodsList
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