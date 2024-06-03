@echo off
setlocal
set ROOT_DIR=%~dp0..\..\..\..
set VSROOT_DIR=%~dp0..\..
call "%ROOT_DIR%\node.exe" "%VSROOT_DIR%\out\server-cli.js" "code-server" "1.87.0" "e5d145fdf32e82be2a0f8de9a3b05f65b13052b6" "code-server.cmd" %*
endlocal
