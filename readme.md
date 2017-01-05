## TODO

1. Bug: Changes in imported files are not generating a refresh of the client Lib (only the files stated in css.txt)
    * Solution: Create a listener for all scss files and refresh the clientlibs where these are loaded. 
               
               - Might not be performant. 
               Potential solution: Trigger an event when compiling a clientLib to create a map of all used scss files. 
               Based on the map you can figure out quickly what clientLibs needs to be recompiled.
               
2. Provide an OSGI Service for compilation (low prio)
