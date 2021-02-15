Background
----------

We support two models of importing analyzer results. The older model,
which we are phasing out, has the code for importing analyzer results as
part of the core of OpenELIS. The newer model which all new analyzers
should use is a plugin model.

Basics
------

There is a directory /plugin in OpenElis. Any jar file in this directory
will be processed and if it is a valid analyzer plugin it will be added
to the application. If the plugin is later removed any imported data
will be retained but the analyzer will no longer appear in the menu and
openELIS will not accept analyzer files meant for that analyzer.

The plugins are loaded during the application startup. A future feature
will be to enable/disable them during runtime.

Working with plugins
--------------------

Plugins can be developed completely independently of OpenELIS or they
can be checked into
`https://github.com/openelisglobal/openelisglobal-plugins`_. Currently
we are only supporting plugins for analyzers but we expect to also
support plugins for electronic and paper reports. The analyzer plugins
should be added under "analyzer". There is an example called
WeberAnalyzer which can be used as a template. Note that it also
includes a **SMALL** input file sample as well as contact information.

Good manners
^^^^^^^^^^^^

If you want to modify an existing analyzer written by a different
organization please contact the creator of the analyzer and work with
them to make sure that your changes do not break their imports. You
should also include the core group with confirmation that you have the
right to modify the work from a different organization or else we will
not allow the change to be made in repository. An alternative is to make
a copy of the other persons work. Note that you should honor the
copyright notice and name yourself as a contributor, not as the creator.

If you no longer are using a particular analyzer and do not care if the
work is modified please note that in the contact file.

Working with git
----------------

The plugins are in a separate repository from the core but the IDE is
only tracking the core repository. When you do a commit from within the
IDE you are only committing what is in openelisglobal-core, not anything
from openelisglobal-plugin. To maintain that repository you will need to
either work from the command line or use a tool such as `SourceTree`_.

For all IDE's
-------------

The JDK that is being used to compile the plugin **MUST** be as old or
older than the Java version under which tomcat is running. i.e. If
tomcat is using JRE 1.7 and the plugin was compiled with JDK 1.8 then
bad things will happen

Setting up for Eclipse
----------------------

Creation Steps

1. Create a new Java project. Accept all of the defaults. The name of
   the project should match the name of the analyzer.
2. Open up the properties for the project
3. Select the Java build path (left hand side of the dialog)
4. Select projects tab and add (right hand side of the dialog)
   openElisGlobal 1

.. _`https://github.com/openelisglobal/openelisglobal-plugins`: https://github.com/openelisglobal/openelisglobal-plugins
.. _SourceTree: http://www.sourcetreeapp.com/