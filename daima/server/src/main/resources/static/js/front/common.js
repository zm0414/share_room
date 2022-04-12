var domain = "/";
var storeId = 1;
window.T = {};
var url = function(name) {
  var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
  var r = window.location.search.substr(1).match(reg);
  if(r!=null)return  unescape(r[2]); return null;
};
T.p = url;

//选择单条记录（bootstraptable）
function getSelectedVal(key) {
    var grid = $('#table').bootstrapTable('getSelections');
    if(!grid.length){
    	alert("请选择一条记录");
    	return ;
    }
    
    if(grid.length > 1){
    	alert("只能选择一条记录");
    	return ;
    }
    return grid[0][key];
}

//选择多条记录（bootstraptable）
function getSelectedVals(key) {
    var grid = $('#table').bootstrapTable('getSelections');
    if(!grid.length){
    	alert("请选择一条记录");
    	return ;
    }
    var ids = [];
    $.each(grid, function(idx, item){
        ids[idx] = item[key];
    });
    return ids;
}