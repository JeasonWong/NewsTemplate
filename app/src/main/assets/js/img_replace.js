function img_replace(source, replaceSource) {
    $('img[zhimg-src*="'+source+'"]').each(function () {
        $(this).attr('src', replaceSource);
    });
}

var DEFAULT_IMAGE_URI = "file:///android_asset/default_pic_content_image_loading_light.png";
var DEFAULT_LOADING_IMAGE_URI = "file:///android_asset/default_pic_content_image_download_light.png";

function onLoaded() {
	var allImage = document.querySelectorAll("img");
	allImage = Array.prototype.slice.call(allImage, 0);
	allImage.forEach(function(image) {
		if (image.src == DEFAULT_IMAGE_URI) {
			ZhihuDaily.loadImage(image.getAttribute("zhimg-src"));
		}
	});
}

function onImageClick(pImage) {
	console.log(pImage);
	if (pImage.src == DEFAULT_LOADING_IMAGE_URI) {
		ZhihuDaily.clickToLoadImage(pImage.getAttribute("zhimg-src"));
	} else {
		ZhihuDaily.openImage(pImage.getAttribute("zhimg-src"));
	}
};

function onImageLoadingComplete(pOldUrl, pNewUrl) {
	var allImage = document.querySelectorAll("img");
	allImage = Array.prototype.slice.call(allImage, 0);
	allImage.forEach(function(image) {
		if (image.getAttribute("zhimg-src") == pOldUrl || image.getAttribute("zhimg-src") == decodeURIComponent(pOldUrl)) {
			image.src = pNewUrl;
		}
	});
}