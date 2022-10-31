function activityMouseover(element) {
//    element.style.border = "2px solid gray";
//    element.style.padding = "8px";
    element.style.boxShadow = "0px 0px 16px gray";
//    element.style.cursor = "pointer";
}

function activityMouseout(element) {
//    element.style.border = "0px";
//    element.style.padding = "10px";
    element.style.boxShadow = "0px 0px 0px";
}

function postMouseover(element) {
	element.style.background = "rgba(255, 255, 255, 0.9)";
}

function postMouseout(element) {
	element.style.background = "rgba(255, 255, 255, 0.7)";
}