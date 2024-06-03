"use strict";/*!--------------------------------------------------------
 * Copyright (C) Microsoft Corporation. All rights reserved.
 *--------------------------------------------------------*/(function(){var I=["require","exports","vs/base/common/ternarySearchTree","vs/platform/instantiation/common/instantiation","vs/platform/profiling/common/profiling","vs/base/common/path","vs/platform/profiling/common/profilingModel","vs/base/common/arrays","vs/base/common/strings","vs/platform/profiling/electron-sandbox/profileAnalysisWorker","vs/base/common/uri"],E=function(k){for(var o=[],g=0,v=k.length;g<v;g++)o[g]=I[k[g]];return o};define(I[2],E([0,1,7,8]),function(k,o,g,v){"use strict";Object.defineProperty(o,"__esModule",{value:!0}),o.$Ci=o.$Bi=o.$Ai=o.$zi=o.$yi=void 0;class y{constructor(){this.b="",this.c=0}reset(e){return this.b=e,this.c=0,this}next(){return this.c+=1,this}hasNext(){return this.c<this.b.length-1}cmp(e){const i=e.charCodeAt(0),n=this.b.charCodeAt(this.c);return i-n}value(){return this.b[this.c]}}o.$yi=y;class p{constructor(e=!0){this.e=e}reset(e){return this.b=e,this.c=0,this.d=0,this.next()}hasNext(){return this.d<this.b.length}next(){this.c=this.d;let e=!0;for(;this.d<this.b.length;this.d++)if(this.b.charCodeAt(this.d)===46)if(e)this.c++;else break;else e=!1;return this}cmp(e){return this.e?(0,v.$sf)(e,this.b,0,e.length,this.c,this.d):(0,v.$uf)(e,this.b,0,e.length,this.c,this.d)}value(){return this.b.substring(this.c,this.d)}}o.$zi=p;class m{constructor(e=!0,i=!0){this.f=e,this.g=i}reset(e){this.d=0,this.e=0,this.b=e,this.c=e.length;for(let i=e.length-1;i>=0;i--,this.c--){const n=this.b.charCodeAt(i);if(!(n===47||this.f&&n===92))break}return this.next()}hasNext(){return this.e<this.c}next(){this.d=this.e;let e=!0;for(;this.e<this.c;this.e++){const i=this.b.charCodeAt(this.e);if(i===47||this.f&&i===92)if(e)this.d++;else break;else e=!1}return this}cmp(e){return this.g?(0,v.$sf)(e,this.b,0,e.length,this.d,this.e):(0,v.$uf)(e,this.b,0,e.length,this.d,this.e)}value(){return this.b.substring(this.d,this.e)}}o.$Ai=m;var h;(function(f){f[f.Scheme=1]="Scheme",f[f.Authority=2]="Authority",f[f.Path=3]="Path",f[f.Query=4]="Query",f[f.Fragment=5]="Fragment"})(h||(h={}));class u{constructor(e,i){this.f=e,this.g=i,this.d=[],this.e=0}reset(e){return this.c=e,this.d=[],this.c.scheme&&this.d.push(1),this.c.authority&&this.d.push(2),this.c.path&&(this.b=new m(!1,!this.f(e)),this.b.reset(e.path),this.b.value()&&this.d.push(3)),this.g(e)||(this.c.query&&this.d.push(4),this.c.fragment&&this.d.push(5)),this.e=0,this}next(){return this.d[this.e]===3&&this.b.hasNext()?this.b.next():this.e+=1,this}hasNext(){return this.d[this.e]===3&&this.b.hasNext()||this.e<this.d.length-1}cmp(e){if(this.d[this.e]===1)return(0,v.$tf)(e,this.c.scheme);if(this.d[this.e]===2)return(0,v.$tf)(e,this.c.authority);if(this.d[this.e]===3)return this.b.cmp(e);if(this.d[this.e]===4)return(0,v.$rf)(e,this.c.query);if(this.d[this.e]===5)return(0,v.$rf)(e,this.c.fragment);throw new Error}value(){if(this.d[this.e]===1)return this.c.scheme;if(this.d[this.e]===2)return this.c.authority;if(this.d[this.e]===3)return this.b.value();if(this.d[this.e]===4)return this.c.query;if(this.d[this.e]===5)return this.c.fragment;throw new Error}}o.$Bi=u;class d{constructor(){this.height=1}isEmpty(){return!this.left&&!this.mid&&!this.right&&!this.value}rotateLeft(){const e=this.right;return this.right=e.left,e.left=this,this.updateHeight(),e.updateHeight(),e}rotateRight(){const e=this.left;return this.left=e.right,e.right=this,this.updateHeight(),e.updateHeight(),e}updateHeight(){this.height=1+Math.max(this.heightLeft,this.heightRight)}balanceFactor(){return this.heightRight-this.heightLeft}get heightLeft(){return this.left?.height??0}get heightRight(){return this.right?.height??0}}var b;(function(f){f[f.Left=-1]="Left",f[f.Mid=0]="Mid",f[f.Right=1]="Right"})(b||(b={}));class l{static forUris(e=()=>!1,i=()=>!1){return new l(new u(e,i))}static forPaths(e=!1){return new l(new m(void 0,!e))}static forStrings(){return new l(new y)}static forConfigKeys(){return new l(new p)}constructor(e){this.b=e}clear(){this.c=void 0}fill(e,i){if(i){const n=i.slice(0);(0,g.$Xb)(n);for(const s of n)this.set(s,e)}else{const n=e.slice(0);(0,g.$Xb)(n);for(const s of n)this.set(s[0],s[1])}}set(e,i){const n=this.b.reset(e);let s;this.c||(this.c=new d,this.c.segment=n.value());const t=[];for(s=this.c;;){const c=n.cmp(s.segment);if(c>0)s.left||(s.left=new d,s.left.segment=n.value()),t.push([-1,s]),s=s.left;else if(c<0)s.right||(s.right=new d,s.right.segment=n.value()),t.push([1,s]),s=s.right;else if(n.hasNext())n.next(),s.mid||(s.mid=new d,s.mid.segment=n.value()),t.push([0,s]),s=s.mid;else break}const r=s.value;s.value=i,s.key=e;for(let c=t.length-1;c>=0;c--){const a=t[c][1];a.updateHeight();const T=a.balanceFactor();if(T<-1||T>1){const $=t[c][0],w=t[c+1][0];if($===1&&w===1)t[c][1]=a.rotateLeft();else if($===-1&&w===-1)t[c][1]=a.rotateRight();else if($===1&&w===-1)a.right=t[c+1][1]=t[c+1][1].rotateRight(),t[c][1]=a.rotateLeft();else if($===-1&&w===1)a.left=t[c+1][1]=t[c+1][1].rotateLeft(),t[c][1]=a.rotateRight();else throw new Error;if(c>0)switch(t[c-1][0]){case-1:t[c-1][1].left=t[c][1];break;case 1:t[c-1][1].right=t[c][1];break;case 0:t[c-1][1].mid=t[c][1];break}else this.c=t[0][1]}}return r}get(e){return this.d(e)?.value}d(e){const i=this.b.reset(e);let n=this.c;for(;n;){const s=i.cmp(n.segment);if(s>0)n=n.left;else if(s<0)n=n.right;else if(i.hasNext())i.next(),n=n.mid;else break}return n}has(e){const i=this.d(e);return!(i?.value===void 0&&i?.mid===void 0)}delete(e){return this.e(e,!1)}deleteSuperstr(e){return this.e(e,!0)}e(e,i){const n=this.b.reset(e),s=[];let t=this.c;for(;t;){const r=n.cmp(t.segment);if(r>0)s.push([-1,t]),t=t.left;else if(r<0)s.push([1,t]),t=t.right;else if(n.hasNext())n.next(),s.push([0,t]),t=t.mid;else break}if(t){if(i?(t.left=void 0,t.mid=void 0,t.right=void 0,t.height=1):(t.key=void 0,t.value=void 0),!t.mid&&!t.value)if(t.left&&t.right){const r=this.f(t.right);if(r.key){const{key:c,value:a,segment:T}=r;this.e(r.key,!1),t.key=c,t.value=a,t.segment=T}}else{const r=t.left??t.right;if(s.length>0){const[c,a]=s[s.length-1];switch(c){case-1:a.left=r;break;case 0:a.mid=r;break;case 1:a.right=r;break}}else this.c=r}for(let r=s.length-1;r>=0;r--){const c=s[r][1];c.updateHeight();const a=c.balanceFactor();if(a>1?(c.right.balanceFactor()>=0||(c.right=c.right.rotateRight()),s[r][1]=c.rotateLeft()):a<-1&&(c.left.balanceFactor()<=0||(c.left=c.left.rotateLeft()),s[r][1]=c.rotateRight()),r>0)switch(s[r-1][0]){case-1:s[r-1][1].left=s[r][1];break;case 1:s[r-1][1].right=s[r][1];break;case 0:s[r-1][1].mid=s[r][1];break}else this.c=s[0][1]}}}f(e){for(;e.left;)e=e.left;return e}findSubstr(e){const i=this.b.reset(e);let n=this.c,s;for(;n;){const t=i.cmp(n.segment);if(t>0)n=n.left;else if(t<0)n=n.right;else if(i.hasNext())i.next(),s=n.value||s,n=n.mid;else break}return n&&n.value||s}findSuperstr(e){return this.g(e,!1)}g(e,i){const n=this.b.reset(e);let s=this.c;for(;s;){const t=n.cmp(s.segment);if(t>0)s=s.left;else if(t<0)s=s.right;else if(n.hasNext())n.next(),s=s.mid;else return s.mid?this.h(s.mid):i?s.value:void 0}}hasElementOrSubtree(e){return this.g(e,!0)!==void 0}forEach(e){for(const[i,n]of this)e(n,i)}*[Symbol.iterator](){yield*this.h(this.c)}h(e){const i=[];return this.j(e,i),i[Symbol.iterator]()}j(e,i){e&&(e.left&&this.j(e.left,i),e.value&&i.push([e.key,e.value]),e.mid&&this.j(e.mid,i),e.right&&this.j(e.right,i))}_isBalanced(){const e=i=>{if(!i)return!0;const n=i.balanceFactor();return n<-1||n>1?!1:e(i.left)&&e(i.right)};return e(this.c)}}o.$Ci=l}),define(I[3],E([0,1]),function(k,o){"use strict";Object.defineProperty(o,"__esModule",{value:!0}),o.$vi=o._util=void 0,o.$wi=y,o.$xi=p;var g;(function(m){m.serviceIds=new Map,m.DI_TARGET="$di$target",m.DI_DEPENDENCIES="$di$dependencies";function h(u){return u[m.DI_DEPENDENCIES]||[]}m.getServiceDependencies=h})(g||(o._util=g={})),o.$vi=y("instantiationService");function v(m,h,u){h[g.DI_TARGET]===h?h[g.DI_DEPENDENCIES].push({id:m,index:u}):(h[g.DI_DEPENDENCIES]=[{id:m,index:u}],h[g.DI_TARGET]=h)}function y(m){if(g.serviceIds.has(m))return g.serviceIds.get(m);const h=function(u,d,b){if(arguments.length!==3)throw new Error("@IServiceName-decorator can only be used to decorate a parameter");v(h,u,b)};return h.toString=()=>m,g.serviceIds.set(m,h),h}function p(m){return m}}),define(I[4],E([0,1,5,3]),function(k,o,g,v){"use strict";Object.defineProperty(o,"__esModule",{value:!0}),o.Utils=o.$vG=void 0,o.$vG=(0,v.$wi)("IV8InspectProfilingService");var y;(function(p){function m(u){return!!(u.samples&&u.timeDeltas)}p.isValidProfile=m;function h(u,d="noAbsolutePaths"){for(const b of u.nodes)b.callFrame&&b.callFrame.url&&((0,g.$jc)(b.callFrame.url)||/^\w[\w\d+.-]*:\/\/\/?/.test(b.callFrame.url))&&(b.callFrame.url=(0,g.$kc)(d,(0,g.$oc)(b.callFrame.url)));return u}p.rewriteAbsolutePaths=h})(y||(o.Utils=y={}))}),define(I[6],E([0,1]),function(k,o){"use strict";Object.defineProperty(o,"__esModule",{value:!0}),o.$Aic=o.$zic=o.$yic=void 0;const g=(h,u)=>{const d=u[h];if(d.aggregateTime)return d.aggregateTime;let b=d.selfTime;for(const l of d.children)b+=g(l,u);return d.aggregateTime=b},v=h=>{let u=0;const d=new Map,b=l=>{const f=[l.functionName,l.url,l.scriptId,l.lineNumber,l.columnNumber].join(":"),e=d.get(f);if(e)return e.id;const i=u++;return d.set(f,{id:i,callFrame:l,location:{lineNumber:l.lineNumber+1,columnNumber:l.columnNumber+1}}),i};for(const l of h.nodes)l.locationId=b(l.callFrame),l.positionTicks=l.positionTicks?.map(f=>({...f,startLocationId:b({...l.callFrame,lineNumber:f.line-1,columnNumber:0}),endLocationId:b({...l.callFrame,lineNumber:f.line,columnNumber:0})}));return[...d.values()].sort((l,f)=>l.id-f.id).map(l=>({locations:[l.location],callFrame:l.callFrame}))},y=h=>{if(!h.timeDeltas||!h.samples)return{nodes:[],locations:[],samples:h.samples||[],timeDeltas:h.timeDeltas||[],duration:h.endTime-h.startTime};const{samples:u,timeDeltas:d}=h,l=v(h).map((t,r)=>{const c=t.locations[0];return{id:r,selfTime:0,aggregateTime:0,ticks:0,callFrame:t.callFrame,src:c}}),f=new Map,e=t=>{let r=f.get(t);return r===void 0&&(r=f.size,f.set(t,r)),r},i=new Array(h.nodes.length);for(let t=0;t<h.nodes.length;t++){const r=h.nodes[t],c=e(r.id);i[c]={id:c,selfTime:0,aggregateTime:0,locationId:r.locationId,children:r.children?.map(e)||[]};for(const a of r.positionTicks||[])a.startLocationId&&(l[a.startLocationId].ticks+=a.ticks)}for(const t of i)for(const r of t.children)i[r].parent=t.id;const n=h.endTime-h.startTime;let s=n-d[0];for(let t=0;t<d.length-1;t++){const r=d[t+1];i[e(u[t])].selfTime+=r,s-=r}i.length&&(i[e(u[d.length-1])].selfTime+=s,d.push(s));for(let t=0;t<i.length;t++){const r=i[t],c=l[r.locationId];c.aggregateTime+=g(t,i),c.selfTime+=r.selfTime}return{nodes:i,locations:l,samples:u.map(e),timeDeltas:d,duration:n}};o.$yic=y;class p{static root(){return new p({id:-1,selfTime:0,aggregateTime:0,ticks:0,callFrame:{functionName:"(root)",lineNumber:-1,columnNumber:-1,scriptId:"0",url:""}})}get id(){return this.location.id}get callFrame(){return this.location.callFrame}get src(){return this.location.src}constructor(u,d){this.location=u,this.parent=d,this.children={},this.aggregateTime=0,this.selfTime=0,this.ticks=0,this.childrenSize=0}addNode(u){this.selfTime+=u.selfTime,this.aggregateTime+=u.aggregateTime}}o.$zic=p;const m=(h,u,d,b=u)=>{let l=h.children[u.locationId];l||(l=new p(d.locations[u.locationId],h),h.childrenSize++,h.children[u.locationId]=l),l.addNode(b),u.parent&&(0,o.$Aic)(l,d.nodes[u.parent],d,b)};o.$Aic=m}),define(I[9],E([0,1,5,2,10,4,6]),function(k,o,g,v,y,p,m){"use strict";Object.defineProperty(o,"__esModule",{value:!0}),o.create=h;function h(){return new u}class u{analyseBottomUp(n){if(!p.Utils.isValidProfile(n))return{kind:1,samples:[]};const s=(0,m.$yic)(n),t=e(s,5).filter(r=>!r.isSpecial);return t.length===0||t[0].percentage<10?{kind:1,samples:[]}:{kind:2,samples:t}}analyseByUrlCategory(n,s){const t=v.$Ci.forUris();t.fill(s);const r=(0,m.$yic)(n),c=new Map;for(const T of r.nodes){const $=r.locations[T.locationId];let w;try{w=t.findSubstr(y.URI.parse($.callFrame.url))}catch{}w||(w=b($.callFrame));const F=(c.get(w)??0)+T.selfTime;c.set(w,F)}const a=[];for(const[T,$]of c)a.push([T,$]);return a}}function d(i){return i.functionName.startsWith("(")&&i.functionName.endsWith(")")}function b(i){let n=i.functionName||"(anonymous)";return i.url&&(n+="#",n+=(0,g.$oc)(i.url),i.lineNumber>=0&&(n+=":",n+=i.lineNumber+1),i.columnNumber>=0&&(n+=":",n+=i.columnNumber+1)),n}function l(i){let n=i.functionName||"(anonymous)";return i.url&&(n+=" (",n+=i.url,i.lineNumber>=0&&(n+=":",n+=i.lineNumber+1),i.columnNumber>=0&&(n+=":",n+=i.columnNumber+1),n+=")"),n}function f(i,n){const s={};for(const r of i.nodes)s[r.locationId]=(s[r.locationId]||0)+r.selfTime;const t=Object.entries(s).sort(([,r],[,c])=>c-r).slice(0,n).map(([r])=>Number(r));return new Set(t)}function e(i,n){const s=m.$zic.root(),t=f(i,n);for(const a of i.nodes)t.has(a.locationId)&&((0,m.$Aic)(s,a,i),s.addNode(a));const r=Object.values(s.children).sort((a,T)=>T.selfTime-a.selfTime).slice(0,n),c=[];for(const a of r){const T={selfTime:Math.round(a.selfTime/1e3),totalTime:Math.round(a.aggregateTime/1e3),location:b(a.callFrame),absLocation:l(a.callFrame),url:a.callFrame.url,caller:[],percentage:Math.round(a.selfTime/(i.duration/100)),isSpecial:d(a.callFrame)},$=[a];for(;$.length;){const w=$.pop();let N;for(const F of Object.values(w.children))(!N||N.selfTime<F.selfTime)&&(N=F);if(N){const F=Math.round(N.selfTime/(w.selfTime/100));T.caller.push({percentage:F,location:b(N.callFrame),absLocation:l(N.callFrame)}),$.push(N)}}c.push(T)}return c}})}).call(this);

//# sourceMappingURL=profileAnalysisWorker.js.map
