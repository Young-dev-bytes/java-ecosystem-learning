"use strict";/*!--------------------------------------------------------
 * Copyright (C) Microsoft Corporation. All rights reserved.
 *--------------------------------------------------------*/(function(){var v=["vs/code/browser/workbench/workbench","require","exports","vs/base/browser/browser","vs/base/browser/window","vs/base/common/buffer","vs/base/common/event","vs/base/common/lifecycle","vs/base/common/marshalling","vs/base/common/network","vs/base/common/path","vs/base/common/resources","vs/base/common/strings","vs/base/common/uri","vs/platform/product/common/product","vs/platform/window/common/window","vs/platform/tunnel/common/tunnel","vs/workbench/workbench.web.main"],C=function(b){for(var m=[],f=0,a=b.length;f<a;f++)m[f]=v[b[f]];return m};define(v[0],C([1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17]),function(b,m,f,a,$,S,k,R,p,g,w,T,u,U,d,Q,O){"use strict";Object.defineProperty(m,"__esModule",{value:!0}),m.LocalStorageSecretStorageProvider=void 0;class L{async seal(e){return e}async unseal(e){return e}}var I;(function(c){c.ALGORITHM="AES-GCM",c[c.KEY_LENGTH=256]="KEY_LENGTH",c[c.IV_LENGTH=12]="IV_LENGTH"})(I||(I={}));class E{static supported(){return!!crypto.subtle}constructor(e){this.b=e}async seal(e){const t=a.$gQ.crypto.getRandomValues(new Uint8Array(12)),r=await a.$gQ.crypto.subtle.generateKey({name:"AES-GCM",length:256},!0,["encrypt","decrypt"]),s=new Uint8Array(await a.$gQ.crypto.subtle.exportKey("raw",r)),n=await this.c(s),o=new TextEncoder().encode(e),l=await a.$gQ.crypto.subtle.encrypt({name:"AES-GCM",iv:t},n,o),h=new Uint8Array([...s,...t,...new Uint8Array(l)]);return(0,$.$Me)($.$se.wrap(h))}async unseal(e){const t=(0,$.$Le)(e);if(t.byteLength<60)throw Error("Invalid length for the value for credentials.crypto");const r=256/8,s=t.slice(0,r),n=t.slice(r,r+12),o=t.slice(r+12),l=await this.c(s.buffer),h=await a.$gQ.crypto.subtle.decrypt({name:"AES-GCM",iv:n.buffer},l,o.buffer);return new TextDecoder().decode(new Uint8Array(h))}async c(e){if(!e||e.byteLength!==256/8)throw Error("Invalid length for clientKey");const t=await this.d(),r=new Uint8Array(256/8);for(let s=0;s<r.byteLength;s++)r[s]=e[s]^t[s];return a.$gQ.crypto.subtle.importKey("raw",r,{name:"AES-GCM",length:256},!0,["encrypt","decrypt"])}async d(){if(this.a)return this.a;let e=0,t;for(;e<=3;)try{const r=await fetch(this.b,{credentials:"include",method:"POST"});if(!r.ok)throw new Error(r.statusText);const s=new Uint8Array(await await r.arrayBuffer());if(s.byteLength!==256/8)throw Error("The key retrieved by the server is not 256 bit long.");return this.a=s,this.a}catch(r){t=r,e++,await new Promise(s=>setTimeout(s,e*e*100))}throw t}}class A{constructor(e){this.c=e,this.a="secrets.provider",this.b=this.d(),this.type="persisted"}async d(){const e=this.f(),t=localStorage.getItem(this.a);if(t)try{const r=JSON.parse(await this.c.unseal(t));return{...e,...r}}catch(r){console.error("Failed to decrypt secrets from localStorage",r),localStorage.removeItem(this.a)}return e}f(){let e;const t=a.$gQ.document.getElementById("vscode-workbench-auth-session"),r=t?t.getAttribute("data-settings"):void 0;if(r)try{e=JSON.parse(r)}catch{}if(!e)return{};const s={};if(s[`${U.default.urlProtocol}.loginAccount`]=JSON.stringify(e),e.providerId!=="github")return console.error(`Unexpected auth provider: ${e.providerId}. Expected 'github'.`),s;const n=JSON.stringify({extensionId:"vscode.github-authentication",key:"github.auth"});return s[n]=JSON.stringify(e.scopes.map(o=>({id:e.id,scopes:o,accessToken:e.accessToken}))),s}async get(e){return(await this.b)[e]}async set(e,t){const r=await this.b;r[e]=t,this.b=Promise.resolve(r),this.g()}async delete(e){const t=await this.b;delete t[e],this.b=Promise.resolve(t),this.g()}async g(){try{const e=await this.c.seal(JSON.stringify(await this.b));localStorage.setItem(this.a,e)}catch(e){console.error(e)}}}m.LocalStorageSecretStorageProvider=A;class y extends k.$Tc{constructor(e){super(),this.m=e,this.c=this.B(new S.$5d),this.onCallback=this.c.event,this.f=new Set,this.g=Date.now(),this.h=void 0}create(e={}){const t=++y.a,r=[`vscode-reqid=${t}`];for(const n of y.b){const o=e[n];o&&r.push(`vscode-${n}=${encodeURIComponent(o)}`)}if(!(e.authority==="vscode.github-authentication"&&e.path==="/dummy")){const n=`vscode-web.url-callbacks[${t}]`;localStorage.removeItem(n),this.f.add(t),this.n()}const s=(a.$gQ.location.pathname+"/"+this.m).replace(/\/\/+/g,"/");return u.URI.parse(a.$gQ.location.href).with({path:s,query:r.join("&")})}n(){if(this.j)return;const e=()=>this.s();a.$gQ.addEventListener("storage",e),this.j={dispose:()=>a.$gQ.removeEventListener("storage",e)}}r(){this.j?.dispose(),this.j=void 0}async s(){const e=Date.now()-this.g;e>1e3?this.t():this.h===void 0&&(this.h=setTimeout(()=>{this.h=void 0,this.t()},1e3-e))}t(){let e;for(const t of this.f){const r=`vscode-web.url-callbacks[${t}]`,s=localStorage.getItem(r);if(s!==null){try{this.c.fire(u.URI.revive(JSON.parse(s)))}catch(n){console.error(n)}e=e??new Set(this.f),e.delete(t),localStorage.removeItem(r)}}e&&(this.f=e,this.f.size===0&&this.r()),this.g=Date.now()}}y.a=0,y.b=["scheme","authority","path","query","fragment"];class i{static create(e){let t=!1,r,s=Object.create(null);return new URL(document.location.href).searchParams.forEach((o,l)=>{switch(l){case i.b:e.remoteAuthority&&o.startsWith(g.$gc.sep)?r={folderUri:u.URI.from({scheme:p.Schemas.vscodeRemote,path:o,authority:e.remoteAuthority})}:r={folderUri:u.URI.parse(o)},t=!0;break;case i.c:e.remoteAuthority&&o.startsWith(g.$gc.sep)?r={workspaceUri:u.URI.from({scheme:p.Schemas.vscodeRemote,path:o,authority:e.remoteAuthority})}:r={workspaceUri:u.URI.parse(o)},t=!0;break;case i.a:r=void 0,t=!0;break;case i.d:try{s=(0,R.$Mh)(o)}catch(h){console.error(h)}break}}),t||(e.folderUri?r={folderUri:u.URI.revive(e.folderUri)}:e.workspaceUri&&(r={workspaceUri:u.URI.revive(e.workspaceUri)})),new i(r,s,e)}constructor(e,t,r){this.workspace=e,this.payload=t,this.f=r,this.trusted=!0}async open(e,t){if(t?.reuse&&!t.payload&&this.j(this.workspace,e))return!0;const r=this.g(e,t);if(r){if(t?.reuse)return a.$gQ.location.href=r,!0;{let s;return(0,f.$AQ)()?s=a.$gQ.open(r,"_blank","toolbar=no"):s=a.$gQ.open(r),!!s}}return!1}g(e,t){let r;if(!e)r=`${document.location.origin}${document.location.pathname}?${i.a}=true`;else if((0,d.$4C)(e)){const s=this.h(e.folderUri);r=`${document.location.origin}${document.location.pathname}?${i.b}=${s}`}else if((0,d.$3C)(e)){const s=this.h(e.workspaceUri);r=`${document.location.origin}${document.location.pathname}?${i.c}=${s}`}return t?.payload&&(r+=`&${i.d}=${encodeURIComponent(JSON.stringify(t.payload))}`),r}h(e){return this.f.remoteAuthority&&e.scheme===p.Schemas.vscodeRemote?encodeURIComponent(`${g.$gc.sep}${(0,T.$3e)(e.path,g.$gc.sep)}`).replaceAll("%2F","/"):encodeURIComponent(e.toString(!0))}j(e,t){return!e||!t?e===t:(0,d.$4C)(e)&&(0,d.$4C)(t)?(0,w.$Kg)(e.folderUri,t.folderUri):(0,d.$3C)(e)&&(0,d.$3C)(t)?(0,w.$Kg)(e.workspaceUri,t.workspaceUri):!1}hasRemote(){if(this.workspace){if((0,d.$4C)(this.workspace))return this.workspace.folderUri.scheme===p.Schemas.vscodeRemote;if((0,d.$3C)(this.workspace))return this.workspace.workspaceUri.scheme===p.Schemas.vscodeRemote}return!0}}i.a="ew",i.b="folder",i.c="workspace",i.d="payload",function(){const c=a.$gQ.document.getElementById("vscode-workbench-web-configuration"),e=c?c.getAttribute("data-settings"):void 0;if(!c||!e)throw new Error("Missing web configuration element");const t={...JSON.parse(e),remoteAuthority:location.host},r=(window.location.pathname+"/mint-key").replace(/\/\/+/g,"/"),s=r&&E.supported()?new E(r):new L;(0,O.create)(a.$gQ.document.body,{...t,windowIndicator:t.windowIndicator??{label:"$(remote)",tooltip:`${U.default.nameShort} Web`},settingsSyncOptions:t.settingsSyncOptions?{enabled:t.settingsSyncOptions.enabled}:void 0,workspaceProvider:i.create(t),urlCallbackProvider:new y(t.callbackRoute),resolveExternalUri:n=>{let o=n;const l=(0,Q.$Qv)(o);if(l&&o.authority!==location.host)if(t.productConfiguration&&t.productConfiguration.proxyEndpointTemplate){const h=t.productConfiguration.proxyEndpointTemplate.replace("{{port}}",l.port.toString()).replace("{{host}}",window.location.host);o=u.URI.parse(new URL(h,window.location.href).toString())}else throw new Error(`Failed to resolve external URI: ${n.toString()}. Could not determine base url because productConfiguration missing.`);return Promise.resolve(o)},tunnelProvider:{tunnelFactory:(n,o)=>{const l=new S.$5d;let h=!1;return Promise.resolve({remoteAddress:n.remoteAddress,localAddress:`localhost:${n.remoteAddress.port}`,onDidDispose:l.event,dispose:()=>{h||(h=!0,l.fire())}})}},secretStorageProvider:t.remoteAuthority&&!r?void 0:new A(s)})}()})}).call(this);

//# sourceMappingURL=workbench.js.map