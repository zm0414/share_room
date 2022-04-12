
const app = getApp();
Page({

  /**
   * 页面的初始数据
   */
  data: {
    height: "",
    seat: [],
    seats: [],
    xyArray: [],
    isSelected: Boolean,
    isSeat: Boolean,
    imgSrc: "/images/icon/seatPre.png",
    imgRoad: "",
    lineTop: 0,
    lineHeight: "",
    lineArray: [],
    reset: false,
    columnNumber: 0,
    selectX: 0,
    selectY: 0,
    selectSeatList: [],
    totalMoney: "",
    totalMoneyFloat: 0.00,
    movie: [],
    price: 0
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function(options) {
    //this.initSeat()
    this.getRoom(options.id)
    this.getSessions(options.date, options.storeId, options.id)
  },

  getSessions(date, storeId, id){
    var that = this;
    wx.request({
      url: app.globalData.domain + '/api/sessions/list',
      data: {
        date: date,
        storeId: storeId,
        roomId: id
      },
      success: function(res) {
        that.setData({
          seats: res.data.sessionsList
        });
        that.showSeat();
      }
    })
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

  initSeat(){
    var row = 6;
    var column = 6;
    var seats = [];
    for(var i = 0; i < row; i++){
      for(var j = 0; j < column; j++){
        var seat = {
          "id": "1",
          "xcoord": j+1,
          "ycoord": i+1,
          "status": 0,
          "type": "danren"
        }
        seats.push(seat)
      }
    }
    console.log(seats)
    this.setData({
      seats: seats
    })
    showSeat()
  },

  showSeat(){
    let xyArray = [];
    let xArray = [];
    let yArray = [];
    for (let a = 0; a < this.data.seats.length; a++) {
      if (yArray.indexOf(this.data.seats[a].ycoord) == -1) {
        yArray.push(this.data.seats[a].ycoord);
      }
    }
    console.log(yArray);
    let totalArray = [];
    for (let b = 0; b < yArray.length; b++) {
      let xxA = [];
      for (let c = 0; c < this.data.seats.length; c++) {
        if (yArray[b] == this.data.seats[c].ycoord) {
          if (this.data.seats[c].type == "danren") {
            if (this.data.seats[c].status == 0) {
              this.data.seats[c].iconSrc = "/images/icon/seatPre.png";
            } else {
              this.data.seats[c].iconSrc = "/images/icon/seatDone.png";
            }
          } else if (this.data.seats[c].type == "road") {
            this.data.seats[c].iconSrc = "";
          }
          this.data.seats[c].select = false;
          xxA.push(this.data.seats[c]);
        }
      }
      totalArray.push(xxA);
    }
    this.setData({
      seat: totalArray,
      lineArray: yArray,
      columnNumber: yArray.length
    })
    console.log(totalArray);

    const query = wx.createSelectorQuery()
    query.select('#seatView').boundingClientRect()
    query.exec((res) => {
      console.log(res);
      let height = res[0].height;
      let newHeight = height / yArray.length;
      let top = res[0].top;
      this.setData({
        lineHeight: newHeight,
        lineTop: top
      })
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

  },
  onChange(e) {
    const query = wx.createSelectorQuery()
    query.select('#seatView').boundingClientRect()
    query.exec((res) => {
      console.log(res);
      let height = res[0].height;
      let top = res[0].top;
      let newHeight = height / this.data.columnNumber;
      this.setData({
        lineHeight: newHeight,
        lineTop: top
      })
    })
  },
  onScale(e) {
    const query = wx.createSelectorQuery()
    query.select('#seatView').boundingClientRect()
    query.exec((res) => {
      console.log(res);
      let height = res[0].height;
      let top = res[0].top;
      let newHeight = height / this.data.columnNumber;
      this.setData({
        lineHeight: newHeight,
        lineTop: top
      })
    })
  },
  selectSeat(e) {
    let x = e.currentTarget.dataset.index + 1;
    let y = e.currentTarget.dataset.ix + 1;
    this.setData({
      selectX: x,
      selectY: y
    })
    let totalArray = this.data.seat;
    for (var a = 0; a < totalArray.length; a++) {
      for (var b = 0; b < totalArray[a].length; b++) {
        let item = totalArray[a][b];
        if (item.ycoord == x && item.xcoord == y &&
          item.status == 0 && item.type != 'road') {
          let totalMoney = 0.00;
          let totalMoneyStr = "";
          if (item.select == true) {
            item.select = false;
            item.iconSrc = "/images/icon/seatPre.png";
            let seatInfo = item.ycoord + "排" + item.xcoord + "座";
            this.remove(seatInfo);
            totalMoney = parseFloat(this.data.totalMoneyFloat) - parseFloat(this.data.price);
            totalMoneyStr = totalMoney.toString();
            if (totalMoney == 0) {
              totalMoneyStr = "";
            }
          } else {
            if (this.data.selectSeatList.length == 6) {
              wx.showToast({
                title: '最多选择6个座位',
                icon: 'none',
                duration: 1000,
                mask: true
              })
            } else {
              item.select = true;
              item.iconSrc = "/images/icon/selectIcon.png";
              let seat = {};
              let seatInfo = item.ycoord + "排" + item.xcoord + "座";
              seat.seatInfo = seatInfo;
              seat.x = item.xcoord;
              seat.y = item.ycoord;
              seat.index = item.xcoord + item.ycoord;
              seat.id = item.id;
              this.data.selectSeatList.push(seat);
              totalMoney = parseFloat(this.data.totalMoneyFloat) + parseFloat(this.data.price);
              totalMoneyStr = totalMoney.toString();
            }
          }
          this.setData({
            selectSeatList: this.data.selectSeatList,
            totalMoney: totalMoneyStr,
            totalMoneyFloat: totalMoney
          })
        }
      }
    }
    this.setData({
      seat: totalArray
    })
  },
  remove(val) {
    for (var a = 0; a < this.data.selectSeatList.length; a++) {
      if (this.data.selectSeatList[a].seatInfo == val) {
        this.data.selectSeatList.splice(a, 1);
        break;
      }
    }
    this.setData({
      selectSeatList: this.data.selectSeatList
    })
  },
  cancelSeat(e) {
    let index = e.currentTarget.dataset.index;
    let x = e.currentTarget.dataset.x;
    let y = e.currentTarget.dataset.y;
    let totalArray = this.data.seat;
    for (var a = 0; a < totalArray.length; a++) {
      for (var b = 0; b < totalArray[a].length; b++) {
        if (totalArray[a][b].xcoord == x && totalArray[a][b].ycoord == y) {
          totalArray[a][b].select = false;
          totalArray[a][b].iconSrc = "/images/icon/seatPre.png";
          break;
        }
      }
    }
    for (var c = 0; c < this.data.selectSeatList.length; c++) {
      if (this.data.selectSeatList[c].index == index) {
        this.data.selectSeatList.splice(c, 1);
        break;
      }
    }
    let totalMoney = this.data.totalMoneyFloat - parseFloat(this.data.price);
    let totalMoneyStr = totalMoney.toString();
    if (totalMoney == 0) {
      totalMoneyStr = "";
    }
    this.setData({
      seat: totalArray,
      selectSeatList: this.data.selectSeatList,
      totalMoney: totalMoneyStr,
      totalMoneyFloat: totalMoney
    })
  },
  submit() {
    var that = this;
    wx.setStorageSync('seatList', that.data.selectSeatList)
    wx.setStorageSync('roomList', [that.data.room])
    wx.navigateTo({
      url: "/pages/order/order-confirm/index?ids=1"
    })
  }

})