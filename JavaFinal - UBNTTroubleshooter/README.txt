Synopsis

At the top of the file there should be a short introduction and/ or overview that explains what the project is. This description should match descriptions added for package managers (Gemspec, package.json, etc.)

This is a program that uses a custom-built troubleshooting tree interface to teach tech support representatives how to troubleshoot with customers over the phone. It's 100% java, and was built with Eclipse.
The primary program is currently called UBNTTroubleshooter.java - I have yet to rename it to WISP-T. It uses a serialized TreeModel as the troubleshooting tree. It has a built in TreeBuilder.java application that allows you to create content for the JTree. It will allow users to create profiles, and enable a "teaching mode" that limits the number of times a user can access a given portion of a tree. Navigation will still work, but the content will be 'gone' unless it is unlocked by an administrator.

Code Example

Show what the library does as concisely as possible, developers should be able to figure out how your project solves their problem by looking at the code example. Make sure the API you are showing off is obvious, and that your code is short and concise.

Motivation

A short description of the motivation behind the creation and maintenance of the project. This should explain why the project exists.

WISPs are differnet from ISPs, and vastly so. Not only are they Wireless(and all that implies), but they are usually smaller, local companies. Competent Tech Support can't just be outsourced - often times, the equipment used is proprietary, used in a unique way, or otherwise impossible to be familiarized with outside of the WISP in question. The WISP I currently work for experiences this issue - they outsource tech support during the wee hours of the morning, but those reps have no idea how to troubleshoot UBNT equipment, let alone the highly local and proprietary MMDS system that was invented by another local WISP.
Were WISPs large companies with lots of resources, training people wouldn't be a problem, but this is often not the case. WISPs are usually comprised of not more than 30 individuals, even while servicing tens of thousands of customers. So every individual most likely occupies an extremely valuable position in the company, with similarly valuable time. A program like this allows such individuals to spend a few hours ONCE on creating a well made, workable troubleshooting tree with which to train multiple people under them, rather than spending a few hours every day, for ONE person's training. Furthermore, in the case of outsourced IT support, it gives the WISP an opportunity to send them an installer with a preconfigured default tree for those who don't need to 'learn' so much as 'perform at a basic capacity'. The company they outource to need only have their employees open the application.

Installation

Provide code examples and explanations of how to get the project.

Feel free to submit your own forks!

License

   Copyright 2017 Austen Loren Strine

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.