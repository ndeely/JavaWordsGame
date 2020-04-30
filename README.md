Computer Architecture, Operating Systems and Networks Assignment
Niall Deely – x19216025@student.ncirl.ie

1.	You try to open a website through a browser on a Local Area Network (LAN), but it fails. In you answer compare 5 network related configuration steps you should check to diagnose and correct the issue. In the context of the network protocol between machines on the LAN, consider the function of Address Resolution Protocol and explain the characteristics of the Ethernet Layer when used with the TCP/IP stack. 


Answer:

If you experience issues accessing a website from a LAN, there are a number of ways to diagnose and try to correct the issue.

To start, you should try to identify where the problem is occurring. If the website is on the internet, the first step is usually to check if the issue is with the specific website itself. If this is the case, the issue could be an external one and not related to the LAN you are operating from. You can do this by checking a number of other popular websites to see if they are still accessible. If they are, the website that is inaccessible may be experiencing issues.

If other websites are also inaccessible, there may be an issue with the connection between the LAN you are using, and the Wide Area Network (WAN) known as the internet. You should ensure that the cable connecting the outside networks to your LAN is inserted correctly into the router, and of the correct specifications for the amount of traffic being sent and received by all of the devices on the LAN. Ensure that the router is correctly connected to the internet, and is sending and receiving traffic by checking the correct lights are on to indicate a connection to the internet, and connect a working device directly to the router to troubleshoot it using the router interface.

If the website you are trying to access is hosted within the LAN, this indicates that the issue is occurring within the LAN. You can try a traceroot to the destination IP address to see how far the packet gets on the route to the destination. This will determine if the issue is occurring within your network, or a node further along the route (in the case of a website hosted outside the LAN). This should be tested multiple times, to ensure you are receiving a consistent result.

If the traceroot stops within your network, check that cables are correctly inserted into the last device that was reached before it stopped.
Additionally, you should test for IP (Internet Protocol) conflicts. If the router or gateway uses Dynamic Host Configuration Protocol (DHCP) to automatically assign IP addresses to computers within the network, you can change your IP address by releasing and renewing it with the command prompt. This will get the router/gateway to assign you a new IP address automatically from it’s available pool if it is functioning correctly. You can also powercycle the router (i.e. turn it off for a few seconds and switch it back on), which should cause the router to reassign IP addresses to all connected devices that are not in conflict. If this does not solve the IP conflict, or the conflict reoccurs, you should replace the router. If the network uses static IP addresses, you can change your IP address manually to something within the acceptable range defined by the router/gateway IP address and subnet mask. You should ensure that the new address you choose does not conflict with any existing devices on the network. If some devices are using static IP addresses while others are not, you could change the devices to use one method or another, and ensure that there are no IP conflicts occurring with the new setup by communicating between the devices.

If the traceroot stops before the router or gateway at an intermediate device such as a switch, you should try different cables between the switch and the next device to see if they may be damaged or broken and are causing the issue. It is also a good idea to check the length of the cables is within limits for the type of cable being used. For example, a CAT 5e cable may not work correctly (or at all), if it is longer than 90 metres. To comply with EIA/TIA wiring standards, individual cables should be limited to 90 metres in length between the wall plate in the work area and the patch panels in the wiring closet.[1]
If they are within this limit, you should also ensure that there is nothing that may cause a lot of electromagnetic interference nearby, as this could cause signal attenuation within the wire and interfere with the connection. If the distance exceeds this limit, multiple wires should be used with a repeater to bridge the gap between them and propagate the signal. You should ensure that these are full-duplex wires, which will allow signals to propagate both ways simultaneously through the wire. 
If multiple devices using this connection are experiencing similar issues, there could be too much traffic on the wire, and you may have to look for a wire that can handle the maximum amount of traffic needed for these devices. For example, a CAT 5e cable provides performance of up to 100MHz, which could be upgraded to a CAT 6 variant (55 metre maximum length, 250 MHz) or CAT 6A (100 metre maximum length, 500 MHz).
If this does not work, there may be an issue with the intermediate device, which should be swapped out for another to see if this fixes the problem.

If the traceroot does not reach any other devices, and you cannot ping the router/gateway or any other connected devices, the issue is with the PC or the connection between the PC and the next device. The wire between the PC and the next device should be tested in the same way as described above.
You can use the IPConfig command in the command line to check your IP configuration and ensure there are no issues with your Network Interface Card (NIC) assigning a Media Access Control (MAC) address. If this is not being correctly assigned, the PC that you are using cannot communicate with other devices on any network, as every computer required a MAC address and an IP address for communication.
A problem with the NIC is usually caused by the correct driver for it not being installed, or the NIC no longer functioning. The correct driver could be found by looking up the make and model of the NIC, and getting the relevant driver from the manufacturer’s website. It is also usually included in some form with the initial purchase, and can be reinstalled this way. However, if the is an old piece of hardware, it may no longer be supported by the manufacturer, or may never have been supported on the Operating System (OS) being used. In this case, the device will have to be replaced.
If there are no IP conflicts, the issue might be caused by software on the PC, such as an overzealous antivirus program or a firewall. These should be temporarily disabled to see if they are the cause.

ARP:
Systems on a network use Address Resolution Protocol (ARP) to maintain a cached table containing contactable IP addresses on the network, and their associated MAC addresses. When you try to contact an IP address, ARP will look up the MAC address for the IP and communicate with this. If it doesn’t find a MAC address for the IP, it will broadcast a message to all devices looking for the machine using this IP. The machine (if there is one) will send back the MAC address it is using, and this can be stored in the ARP table for future reference.
Communication issues can occur when;
a)	A device uses an IP address, but has a different MAC address from the one cached by other devices.
b)	Two devices use the same IP address.
To solve this problem, the ARP table entry for this IP address must be deleted or updated.

TCP/IP and Ethernet:
In the TCP/IP model, ethernet (also known as IEEE 802.3 or CSMA/CD) is part of layer 2, the data-link layer. It provides a set of standards to allow devices to communicate with each other over a network and send information at high speeds. These standards ensure that devices can communicate with each other to create and send frames between nodes on a network, with features such as collision detection to ensure frames are not lost in the process. The nodes are identified by the unique MAC addresses provided by the NIC on each device. These MAC addresses are included in each frame when it is created at the data-link layer.

1)	https://networkencyclopedia.com/horizontal-cabling/


2.	Outline and describe the bottlenecks in at least two computer architectures, and highlight ways by which these bottlenecks may be overcome. Select the architecture of a typical PC with a PCI bus and an ISA bus. Focus on the objectives of Memory Management, and how they are fulfilled in a modern Operating System. Do NOT consider von Neumann architecture to answer the question.


Answer:

MIPS 1:

	MIPS (Microprocessor without Interlocked Pipelined Stages) is a Reduced Instruction Set Computer (RISC) Instruction Set Architecture (ISA). MIPS 1, which was formerly known as MIPS before the introduction of MIPS 2, was introduced in 1985.
The choice to go with a reduced instruction set allowed for the creation of a processor with far fewer transistors than the Complex Instruction Set Computer (CISC) alternative, which also allows for more space for an increased number of registers in the CPU.
MIPS 1 Bottlenecks:
1.	In the MIPS 1, load delay slots were used immediately after lines of code which would load a register from memory. This essentially caused a CPU delay to allow for the time to move an instruction from memory to a register.
However, this meant that if there was not another instruction already loaded to a register that could be run during the delay, which does not depend on the instruction being loaded by the previous instruction or on the result from this instruction, the CPU is simply waiting for the next instruction and is not being used.
A potential solution to this problem could be to load unrelated instructions into empty registers during this delay, or execute the unrelated instruction if there is one already in memory in order to minimise the time spent with the CPU inactive.
In MIPS 2, the load delay slot was removed. They are not often used anymore because load delays are very unpredictable on modern systems, and can be dramatically different depending on the speed of the RAM and other hardware involved.
2.	As with any RISC system, MIPS 1 used a load/store architecture. This can be contrasted with the register/memory architecture used in CISC systems.
If we consider an ADD operation;
In a load/store approach both of the operands and the destination must be in registers. This requires three loads from memory and three free registers (for operands 1 and 2, and the destination for the result in memory).
In a register/memory architecture however, operations can be performed on (or from) memory as well as registers.
This meant that if data were mostly used just once before being discarded, it could be slower to load these into a register first and read them from there. However, if they were used more than once, it would be quicker to first load them to registers and retrieve the values multiple times from there, as getting information from a register is significantly quicker than getting it from memory.
A potential solution to this issue may be to incorporate certain aspects of CISC systems for operations such as this, and trying to combine the better qualities of each type of system. This does seem to be what many hardware manufacturers are doing nowadays to handle memory management better in modern operating systems. Many microprocessors today will use a mixture of RISC and CISC, or are designed to handle conversion from one to another such as a CISC-like ISA that breaks down complex instructions into a larger number of simpler (reduced) instructions. 
3.	As the name MIPS suggests, a major aspect of the design of these microprocessors was to avoid the use of interlocking pipelined stages, and instead to fit every sub-phase, including cache-access, of the instructions into one cycle. The idea was that this would minimise throughput by avoiding the added throughput associated with interlocking, and just allowing for a reduced single cycle throughput. However, this was found to not be worth the trade-off of the more complex CPU/compiler code required, and in later versions of their hardware, interlocking pipelined stages were introduced.
Almost all systems nowadays use interlocking pipelined stages. This means that the hardware will correct issues that occur in a pipeline, and the developer is not required to ensure they have inserted the correct number of NOPs (No Operation commands) for the given hardware to ensure pipeline hazards are avoided, or to account for the different types of hardware that may use the code and their respective delays.

Pentium 2:
The Pentium 2 processor was first introduced by Intel in 1997, and contained many improvements on the preceding Pentium Pro.
While performance with 32-bit software remained largely unchanged, there was significant improvement over the Pentium Pro when working with 16-bit software, which many programs still used and which still made up much of the codebase of Windows 95 drivers. Many of the other major improvements to the Pentium 2 focussed on improving performance while keeping the price of the processor low.
Pentium 2 Bottlenecks:
1.	Early versions of the Pentium 2 processor allowed for only 512MB of RAM to be cached. More RAM than this could be added, but this would not be effectively used and would be very slow. While this did not affect typical home-users much, this was an issue when it came to larger systems such as servers that would frequently require more RAM than this.
The bottleneck here was with the L2 cache controller / tag RAM chip (L2 chip) which had a combined cache of 512MB and ran at half the clock speed of the processor. Significant improvements were later made to later versions of the Pentium 2 processor which increased the clock speed of the L2 chip to match half the performance of the processor’s increased speed, and eventually to release an L2 chip with a cache of 4GB similar to the predecessor Pentium Pro.
In Modern OSs, 32-bit Windows OSs are even now still limited to a maximum RAM capacity of 4GB. This can vary dramatically however, depending on the system hardware and OS being used. For example, a 64-bit Linux system can theoretically support up to 17 billion GB of RAM, though most cap out at 1TB (Intel) or 256TB (AMD64). Given the price of RAM nowadays though, your credit card limit is likely to cap out before any of these.
2.	The L2 cache was not on the processor. As the creation of the Pentium 2 was focussed primarily on reducing costs and improving 16-bit code execution, the L2 cache was not included on the main processor and instead was moved to a back-side bus. This was much slower to access on the bus than on the processor.
To try to ameliorate this issue, the Pentium 2 had a 32KB L1 cache that was on the processor and 4 write buffers, both of which were twice the size of the predecessor’s L1 cache and write buffer count. Additionally, the 4 write buffers could be used by either pipe[2], rather than each only having access to one like it’s predecessor.

The simplest way to solve this bottleneck would be to find a way to put the L2 cache directly on the processor, or otherwise minimise the distance between the L2 cache and the processor. While the steps already taken, such as increasing the L1 cache size and increasing the number of write buffers and their access to pipelines, do a lot to improve the performance, this does not improve the speed of accessing the L2 cache. However, as other solutions, increasing the L1 cache further or adding more pipelines would also help to speed up the system, but may not be cost or space efficient. This issue was solved in Pentium 3 by putting the L2 cache directly onto the processor.

In modern hardware, the distance between the cache and processor has been minimized as much as possible in most systems, the cache size has been dramatically increased to many MB in high-end processors, and CPUs (and software) are often multithreaded now to perform calculations at many times the rates possible before. While this is all positive for performance, there are diminishing returns on many of the techniques being used, such as cache hit rates dropping and cost becoming substantial as cache sizes increase[3]. Regardless, new techniques around cache design, computer architecture, memory management, etc continue to be investigated to look for ways to improve performance in new systems.

[2]  https://ftp.utcluj.ro/pub/docs/publicatii/intel/pentiumII.pdf
[3] https://www.extremetech.com/extreme/188776-how-l1-and-l2-cpu-caches-work-and-why-theyre-an-essential-part-of-modern-chips
