var rutoken=function(n){function t(n){return!!(n&&n.call&&n.apply)}function e(n,e){return t(n[e])?function(){return n[e].apply(n,arguments)}:n[e]}function r(n){return function(){return n}}function i(){n.ready=Promise.resolve(!0),n.isExtensionInstalled=r(Promise.resolve(!1)),n.isPluginInstalled=r(Promise.resolve(!0)),n.loadPlugin=l,window.rutokenLoaded=s}function o(){var t=y.initialize().then(function(){return y.isPluginInstalled()}).then(function(t){return n.isExtensionInstalled=r(Promise.resolve(!0)),n.isPluginInstalled=e(y,"isPluginInstalled"),t&&(m="application/x-rutoken-plugin",n.loadPlugin=a),!0});n.ready=t}function u(){n.ready=Promise.resolve(!0),n.isExtensionInstalled=r(Promise.resolve(!1)),n.isPluginInstalled=r(Promise.resolve(!1))}function l(){var n=document.createElement("object");n.style.setProperty("visibility","hidden","important"),n.style.setProperty("width","0px","important"),n.style.setProperty("height","0px","important"),n.style.setProperty("margin","0px","important"),n.style.setProperty("padding","0px","important"),n.style.setProperty("border-style","none","important"),n.style.setProperty("border-width","0px","important"),n.style.setProperty("max-width","0px","important"),n.style.setProperty("max-height","0px","important"),n.innerHTML="<param name='onload' value='rutokenLoaded'/>",n.setAttribute("type",m),document.body.appendChild(n);var t=new Promise(function(n,t){h.push(n)});return t}function a(){return y.loadPlugin().then(function(n){return c(n)}).then(function(n){return n.wrapWithOldInterface=p,n})}function s(n,t){d(n).then(function(n){if(0==h.length)throw"Internal error";var t=h.shift();t(n)})}function c(n){var r={},i=[];for(var o in n)!function(o){t(n[o].then)?i.push(n[o].then(function(i){return c(i).then(function(i){t(i)?r[o]=e(n,o):r[o]=i})})):r[o]=n[o]}(o);return 0==i.length?new Promise(function(t){t(n)}):Promise.all(i).then(function(){return r})}function p(){var n={};for(var e in this)t(this[e])?n[e]=function(n,t){return function(){var e=arguments[arguments.length-2],r=arguments[arguments.length-1],i=Array.prototype.slice.call(arguments,0,-2);return t.apply(n,i).then(function(n){e(n)},function(n){r(n.message)})}}(this,this[e]):n[e]=this[e];return new Promise(function(t){t(n)})}function f(){var n={originalObject:this.originalObject};for(var t in this.originalObject)n[t]=e(this.originalObject,t);return new Promise(function(t){t(n)})}function d(n){var e={originalObject:n,wrapWithOldInterface:f};for(var r in n)t(n[r])?e[r]=function(n,t){return function(){var e=Array.prototype.slice.call(arguments);return new Promise(function(r,i){e.push(r,i),t.apply(n,e)})}}(n,n[r]):e[r]=n[r];return new Promise(function(n){n(e)})}var h=[],m="application/x-rutoken-pki",y=window["C3B7563B-BF85-45B7-88FC-7CFF1BD3C2DB"];if(y)o();else if(navigator.mimeTypes&&navigator.mimeTypes[m])i();else try{new ActiveXObject("Aktiv.CryptoPlugin");i()}catch(P){u()}return n}(rutoken||{});//"undefined"!=typeof module&&(module.exports=rutoken);
let plugin;

window.onload = function () {
  rutoken.ready.then(function () {
    if (window.chrome || typeof InstallTrigger !== 'undefined') {
      return rutoken.isExtensionInstalled();
    } else {
      return Promise.resolve(true);
    }
  }).then(function (result) {
    if (result) {
      return rutoken.isPluginInstalled();
    } else {
      throw "Rutoken Extension wasn't found";
    }
  }).then(function (result) {
    if (result) {
      return rutoken.loadPlugin();
    } else {
      throw "Rutoken Plugin wasn't found";
    }
  }).then(function (result) {
    plugin = result;
  });
}

export {plugin};
