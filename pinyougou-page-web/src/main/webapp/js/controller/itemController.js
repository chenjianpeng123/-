//ҳ����ϸҳ
app.controller('itemController',function($scope){
	//��¼�û���ѡ�Ĺ��
	$scope.specificationItems={};
	 //��������
	 $scope.addNum=function(x){
		 $scope.num+=x;
		 if($scope.num<1){
			 $scope.num=1;
		 }
	 }
	 
	//�û�ѡ��Ĺ��
	$scope.selectSpecification=function(key,value){
		$scope.specificationItems[key]=value;
		//��ȡsku����
		searchSku();
	}
	
	//�жϹ���Ƿ��û�ѡ��
	$scope.isSelected=function(key,value){
		if($scope.specificationItems[key]==value){
			return true;
		}else{
			return false;
		}
	}
	//��ǰѡ���sku
	$scope.sku={};
	//����Ĭ�ϵ�sku�б�����
	$scope.loadSku=function(){
		$scope.sku=skuList[0];
		$scope.specificationItems= JSON.parse(JSON.stringify($scope.sku.spec)) ;
	}
	
	//ƥ�����������Ƿ����
	matchObject=function(map1,map2){
		for(var k in map1){
			if(map1[k]!=map2[k]){
				return false;
			}
		}
		for(var k in map2){
			if(map2[k]!=map1[k]){
				return false;
			}
		}
		
		return true;
	}
	
  //���ݹ���ѯSKU�б�����
  searchSku=function(){
	  //����skuList
	  for(var i=0;i<skuList.length;i++ ){
		  //�ж��û�ѡ��ĺ����ݿ�������Ƿ����
		  if(matchObject(skuList[i].spec,$scope.specificationItems)){
			  //��ǰѡ��Ͳ�����������
			  $scope.sku=skuList[i];
			  return;
		  }
	
	  }
	  	 //���û��ƥ��
      $scope.sku={id:0,title:'û������',price:0};		
  }
  
  //��ӹ��ﳵ
  $scope.addToCart=function(){
	  alert('skud:'+$scope.sku.id);
  }
	
});