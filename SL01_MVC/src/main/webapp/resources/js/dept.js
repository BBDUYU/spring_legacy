console.log("Dept Module........");

const deptService = (function (){

   // [1] 새로운 부서 추가
   // http://localhost/scott/dept/new   +  POST방식 요청
   //            dept : 입력받은 js Object
    function add(dept, callback, error){
      console.log("> deptService.add()........");
      
      $.ajax({
        type:'post',
        url:'/scott/dept/new',
        data:JSON.stringify(dept),
        contentType : "application/json; charset=utf-8",
        cache:false,
        beforeSend:function (xhr){
          console.log("add.beforeSend ...............");
        },
        success:function (result, status, xhr){          
          if( callback ){
              callback( result );
          } // if
        }, 
        error:  function (xhr, status, er){           
           if( error ){
                error( er );
        } // if  
        } 
      }) 
      .fail(function() {
       alert( "ajax 부서 추가 실패!!!" );
      });
      
   } // add
   
   // [2-1] 부서 삭제
   // http://localhost/scott/dept/50   +  DELETE 방식 요청 
   function remove( deptno , callback, error ){
      console.log("> deptService.remove()........");
            
      //     DELETE http://localhost/scott/dept/$%7B%20deptno%20%7D 400 (Bad Request)
           
      $.ajax({
        type:'delete',
        url:`/scott/dept/${ deptno }`,
        cache:false,
        beforeSend:function (xhr){
          console.log("remove.beforeSend ...............");
        },
        success:function (result, status, xhr){          
          if( callback ){
              callback( result );
          } // if
        }, 
        error: function (xhr, status, er){           
           if( error ){
                error( er );
        } // if  
        } 
      }) 
      .fail(function() {
       alert( "ajax 부서 삭제 실패!!!" );
      });
      
   } // remove
   
   
   // [2-2] 부서 삭제
   // http://localhost/scott/dept/delete?deptno=50 + GET방식 요청
   /*
   function remove( deptno , callback, error ){
      console.log("> deptService.remove()........");
   } // remove
   */
   
   return {
          add    :   add,
          remove :   remove
        }; 

})();