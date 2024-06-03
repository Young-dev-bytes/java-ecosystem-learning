@echo off
setlocal
set ROOT_DIR=%~dp0..\..\..\..
set VSROOT_DIR=%~dp0..\..
call "%ROOT_DIR%\node.exe" "%VSROOT_DIR%\out\server-cli.js" "code-server" "1.89.1" "effc6e95b4ad1c5ac5f9083ec06663ba4a2e005c" "code-server.cmd" %*
endlocal
