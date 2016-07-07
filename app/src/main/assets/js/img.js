function clickImg(){
   var objs = document.getElementsByTagName("img");

   for(var i=0;i<objs.length;i++){
        objs[i].index = i;
        objs[i].onclick=function(){
            window.bazhang.openImg(this.src, this.index);
        }
   }
}