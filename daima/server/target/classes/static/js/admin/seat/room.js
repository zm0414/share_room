$(function () {
	
	var columns = [
        { checkbox: true, align: 'center' },
      	{ title: '图片', field: 'picUrl',
			formatter: function (value, row, index) {
				return '<img width="60px" height="60px" src="'+value+'" />';
			}	
		}, 
		{ title: '房间名称', field: 'roomName'},
		/*{ title: '分类', field: 'category.categoryName'},*/
		{ title: '价格', field: 'price'},
		{ title: '店铺', field: 'store.storeName'},
		/*{ title: '状态', field: 'status', formatter: function(value, row){
			if(value == 0){
				return '<span class="label label-default">已下架</span>';
			}else if(value == 1){
				return '<span class="label label-primary">出售中</span>';
			}
			return '';
		}},*/
		{ title: '创建时间', field: 'createTime'}
		];
	
	$("#table").bootstrapTable({
        url: baseURL + 'room/list',
        cache: false,
        striped: true,
        pagination: true,
        pageSize: 10,
        pageNumber: 1,
        sidePagination: 'server',
        columns: columns,
        queryParams: function (params) {
        	return {
	        	page: params.offset / params.limit + 1,
	        	limit: params.limit,
	        	roomName: vm.q.roomName
        	}
        }
	});
	
});

var vm = new Vue({
	el:'#app',
	data:{
		showList: true,
		title: null,
		room: {
			picUrls: []
		},
		categoryList: [],
		q:{
			roomName: ''
		},
		storeList: []
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.room = {
				picUrls: [],
				categoryId : "",
				storeId: ''
			};
		},
		update: function (event) {
			var id = getSelectedVal("id");
			if(id == null){
				return ;
			}
			vm.showList = false;
            vm.title = "修改";
            vm.room = {
    			picUrls: []
    		};
            
            vm.getInfo(id);
		},
		saveOrUpdate: function (event) {
			if(vm.validator()){
				return;
			}
			
			var url = vm.room.id == null ? "room/save" : "room/update";
			vm.room.picUrl = vm.room.picUrls[0];
			vm.room.describe = ue.getContent();
			$.ajax({
				type: "POST",
			    url: baseURL + url,
                contentType: "application/json",
			    data: JSON.stringify(vm.room),
			    success: function(r){
			    	if(r.code === 0){
						alert('操作成功', function(index){
							vm.reload();
						});
					}else{
						alert(r.msg);
					}
				}
			});
		},
		del: function (event) {
			var ids = getSelectedVals("id");
			if(ids == null){
				return ;
			}
			
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: baseURL + "room/delete",
                    contentType: "application/json",
				    data: JSON.stringify(ids),
				    success: function(r){
						if(r.code == 0){
							alert('操作成功', function(index){
								vm.reload();
							});
						}else{
							alert(r.msg);
						}
					}
				});
			});
		},
		upper: function(){
			var ids = getSelectedVals("id");
			if(ids == null){
				return ;
			}
			confirm('确定要上架选中的记录？', function(){
			$.ajax({
				type: "POST",
			    url: baseURL + "room/upper",
                contentType: "application/json",
			    data: JSON.stringify(ids),
			    success: function(r){
					if(r.code == 0){
						alert('操作成功', function(index){
							vm.reload();
						});
					}else{
						alert(r.msg);
					}
				}
			});
			});
		},
		lower: function(){
			var ids = getSelectedVals("id");
			if(ids == null){
				return ;
			}
			confirm('确定要下架选中的记录？', function(){
			$.ajax({
				type: "POST",
			    url: baseURL + "room/lower",
                contentType: "application/json",
			    data: JSON.stringify(ids),
			    success: function(r){
					if(r.code == 0){
						alert('操作成功', function(index){
							vm.reload();
						});
					}else{
						alert(r.msg);
					}
				}
			});
			});
		},
		getInfo: function(id){
			$.get(baseURL + "room/info/"+id, function(r){
                vm.room = r.room;
                ue.setContent(r.room.describe);
            });
		},
		getCategoryList: function(){
			$.get(baseURL + "category/getAll", function(r){
				vm.categoryList = r.categoryList;
			});
		},
		getStoreList: function(){
			$.get(baseURL + "store/listAll", function(r){
				vm.storeList = r.storeList;
			});
		},
		reload: function (event) {
			vm.showList = true;
			$('#table').bootstrapTable('refresh',  {pageNumber: 1});
		},
		validator: function () {
            if(vm.room.picUrls.length == 0){
                alert("请选择房间图片");
                return true;
            }

            if(isBlank(vm.room.roomName)){
                alert("请填写房间名称");
                return true;
            }
            
            if(isBlank(vm.room.price)){
                alert("请填写价格");
                return true;
            }
        }
	},
	created: function(){
		this.getCategoryList();
		this.getStoreList();
	}
});