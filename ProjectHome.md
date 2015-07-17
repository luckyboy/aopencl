A collection of OpenCL programs ported from X86/X86\_64 to Nexus-4 (and compatible) Android phones/tabs.
  * At present it contains following programs
    * clinfo -- Gives typical CLInfo output for Nexus 4
    * helloworld -- Standard helloworld using OpenCl (android native binary)
    * helloworld2 -- Standard helloworld using OpenCl (android native binary) with generic platform independent OpenCL SDK
    * aparapi -- Initial porting the aparpi to Android on ARM
Without OpenCL SDK from LG/Qualcomm, this example uses 'dlsym ' mapping to get OpenCL API -- thanks for Rahul Garg (who had done initial R&D at https://bitbucket.org/codedivine/testcln10 )

  * At present this project uses OpenCL environment on GPGPU of Nexus 4 (Adreno 320 on APQ8064 Snapdragon S4 Pro). Ideally it should work on other OpenCL environment's also with little modifications .