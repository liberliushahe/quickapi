package com.vitea.util;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.hyperic.sigar.CpuInfo;
import org.hyperic.sigar.CpuPerc;
import org.hyperic.sigar.FileSystem;
import org.hyperic.sigar.FileSystemUsage;
import org.hyperic.sigar.Mem;
import org.hyperic.sigar.NetInterfaceConfig;
import org.hyperic.sigar.NetInterfaceStat;
import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarException;
import org.hyperic.sigar.Swap;

import com.vitea.model.FileSystemInfo;
import com.vitea.model.HostInfo;
import com.vitea.model.MemoryInfo;
import com.vitea.model.NetInfo;
import com.vitea.model.ServerCpuInfo;

/**
 * 获取系统信息
 * @author liushahe
 *
 */
public class SystemInfoUtil {
	/**
	 * 获取系统信息
	 * @throws UnknownHostException
	 */
	public static HostInfo getProperty() throws UnknownHostException {
		HostInfo hostInfo=new HostInfo();
        Runtime r = Runtime.getRuntime();
        Properties props = System.getProperties();
        InetAddress addr;
        addr = InetAddress.getLocalHost();
        String ip = addr.getHostAddress();
        Map<String, String> map = System.getenv();
        // 获取用户名
        String userName = map.get("USERNAME");
       // 获取计算机名
        String computerName = map.get("COMPUTERNAME");
      // 获取计算机域名
        String userDomain = map.get("USERDOMAIN");
        hostInfo.setHostname(userName);
        hostInfo.setComputerName(computerName);
        hostInfo.setUserDomain(userDomain);
        hostInfo.setIp(ip);
        hostInfo.setTotalMemory(r.totalMemory()/1024/1024);
        hostInfo.setFreeMemory(r.freeMemory()/1024/1024);
        hostInfo.setAvailableProcessors(r.availableProcessors());
        hostInfo.setJavaversion(props.getProperty("java.version"));
        hostInfo.setJavavendor(props.getProperty("java.vendor"));
        hostInfo.setJavahome(props.getProperty("java.home"));
        hostInfo.setJavaclassversion(props.getProperty("java.class.version"));
        hostInfo.setOsarch(props.getProperty("os.arch"));
        hostInfo.setOsname(props.getProperty("os.name"));
        hostInfo.setOsversion(props.getProperty("os.version"));
        hostInfo.setJvmname(props.getProperty("java.vm.name"));
        return hostInfo;
    }
	/**
	 * 获取系统内存信息
	 * @throws SigarException
	 */
	public static MemoryInfo getMemory() throws SigarException {
		Sigar sigar = new Sigar();
		Swap swap = sigar.getSwap();
		Mem mem = sigar.getMem();
		MemoryInfo memoryInfo=new MemoryInfo();
		DecimalFormat df = new DecimalFormat("#.00");
		memoryInfo.setUsedpercent(Double.parseDouble(df.format(mem.getUsedPercent())));
		memoryInfo.setTotal(mem.getTotal()/1024/1024/1024);
		memoryInfo.setUsed(mem.getUsed()/1024/1024/1024);
		memoryInfo.setFree(mem.getFree()/1024/1024/1024);
		memoryInfo.setSwaptotal(swap.getTotal()/1024/1024/1024);
		memoryInfo.setSwapused(swap.getUsed()/1024/1024/1024);
		memoryInfo.setSwapfree(swap.getFree()/1024/1024/1024);
		return memoryInfo;
	}
    /**
     * 获取cpu信息
     * @throws SigarException
     */
	public static ServerCpuInfo getCpuInfo() throws SigarException {
        Sigar sigar = new Sigar();
        CpuInfo[] infos = sigar.getCpuInfoList();
        CpuPerc[] cpuList = null;
        cpuList = sigar.getCpuPercList();
        double totalPercent=0.0;
        ServerCpuInfo cpuInfo=new ServerCpuInfo();
      // 不管是单块CPU还是多CPU都适用
        for (int i = 0; i < infos.length; i++) {
            totalPercent+=cpuList[i].getCombined();
        }
        cpuInfo.setCombined(totalPercent);
        return cpuInfo;
    }
	/**
	 * 获取文件系统信息
	 * @return
	 * @throws Exception
	 */
    public static List<FileSystemInfo> getFileInfo() throws Exception {
        Sigar sigar = new Sigar();
        FileSystem[] fslist = sigar.getFileSystemList();
        List<FileSystemInfo> fileSystemInfo=new ArrayList<FileSystemInfo>();
        
        try {
              for (int i = 0; i < fslist.length; i++) {
                FileSystem fs = fslist[i];
                FileSystemInfo fileSystem=new FileSystemInfo();
                FileSystemUsage usage = null;
                usage = sigar.getFileSystemUsage(fs.getDirName());
                fileSystem.setName(fs.getDevName());
                fileSystem.setPath(fs.getDirName());
                fileSystem.setType(fs.getSysTypeName());
                switch (fs.getType()) {
                case 0: 
                    break;
                case 1: 
                    break;
                case 2: 
                	fileSystem.setTotal((usage.getTotal())/1024/1024);
                    fileSystem.setFree(usage.getFree()/1024/1024);
                    fileSystem.setAvail(usage.getAvail()/1024/1024);
                    fileSystem.setUsed(usage.getUsed()/1024/1024);
                    fileSystem.setUsage(usage.getUsePercent() * 100D);
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
                    break;
                default:
                	break;
                }
                fileSystem.setRead(usage.getDiskReads());
                fileSystem.setWrites(usage.getDiskWrites());
                fileSystemInfo.add(fileSystem);
            }
        } catch (Exception e) {

            e.printStackTrace();
        }

        return fileSystemInfo;
    }
   /**
    * 获取网络信息
    * @throws Exception
    */
    public static List<NetInfo> getNetInfo() throws Exception {
        Sigar sigar = new Sigar();
        List<NetInfo> list=new ArrayList<NetInfo>();
        String[] ifNames= sigar.getNetInterfaceList();
        for (int i = 0; i < ifNames.length; i++) {
        	NetInfo info=new NetInfo();
            String name = ifNames[i];
            info.setName(name);
            NetInterfaceConfig ifconfig = sigar.getNetInterfaceConfig(name);
            info.setIp(ifconfig.getAddress());
            if ((ifconfig.getFlags() & 1L) <= 0L) {
                System.out.println("!IFF_UP...skipping getNetInterfaceStat");
                continue;
            }
            NetInterfaceStat ifstat = sigar.getNetInterfaceStat(name);
            info.setSend(ifstat.getTxBytes()/1024/1024);
            info.setReceive(ifstat.getRxBytes()/1024/1024);
           list.add(info);
        }
        return list;
    }

   

}
