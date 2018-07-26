package com.vitea.util;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.DecimalFormat;
import java.util.Map;
import java.util.Properties;

import org.hyperic.sigar.CpuInfo;
import org.hyperic.sigar.CpuPerc;
import org.hyperic.sigar.FileSystem;
import org.hyperic.sigar.FileSystemUsage;
import org.hyperic.sigar.Mem;
import org.hyperic.sigar.NetFlags;
import org.hyperic.sigar.NetInterfaceConfig;
import org.hyperic.sigar.NetInterfaceStat;
import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarException;
import org.hyperic.sigar.Swap;

import com.vitea.model.HostInfo;
import com.vitea.model.MemoryInfo;
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
		memoryInfo.setTotal(mem.getTotal());
		memoryInfo.setUsed(mem.getUsed());
		memoryInfo.setFree(mem.getFree());
		memoryInfo.setSwaptotal(swap.getTotal());
		memoryInfo.setSwapused(swap.getUsed());
		memoryInfo.setSwapfree(swap.getFree());
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
	
    public static void file() throws Exception {
        Sigar sigar = new Sigar();
        FileSystem[] fslist = sigar.getFileSystemList();
        try {
              for (int i = 0; i < fslist.length; i++) {
                System.out.println("分区的盘符名称" + i);
                FileSystem fs = fslist[i];
                // 分区的盘符名称
                System.out.println("盘符名称:    " + fs.getDevName());
                // 分区的盘符名称
                System.out.println("盘符路径:    " + fs.getDirName());
                System.out.println("盘符标志:    " + fs.getFlags());
                // 文件系统类型，比如 FAT32、NTFS
                System.out.println("盘符类型:    " + fs.getSysTypeName());
                // 文件系统类型名，比如本地硬盘、光驱、网络文件系统等
                System.out.println("盘符类型名:    " + fs.getTypeName());
                // 文件系统类型
                System.out.println("盘符文件系统类型:    " + fs.getType());
                FileSystemUsage usage = null;
                usage = sigar.getFileSystemUsage(fs.getDirName());
//                switch (fs.getType()) {
//                case 0: // TYPE_UNKNOWN ：未知
//                    break;
//                case 1: // TYPE_NONE
//                    break;
//                case 2: // TYPE_LOCAL_DISK : 本地硬盘
//                    // 文件系统总大小
//                    System.out.println(fs.getDevName() + "总大小:    " + usage.getTotal() + "KB");
//                    // 文件系统剩余大小
//                    System.out.println(fs.getDevName() + "剩余大小:    " + usage.getFree() + "KB");
//                    // 文件系统可用大小
//                    System.out.println(fs.getDevName() + "可用大小:    " + usage.getAvail() + "KB");
//                    // 文件系统已经使用量
//                    System.out.println(fs.getDevName() + "已经使用量:    " + usage.getUsed() + "KB");
//                    double usePercent = usage.getUsePercent() * 100D;
//                    // 文件系统资源的利用率
//                    System.out.println(fs.getDevName() + "资源的利用率:    " + usePercent + "%");
//                    break;
//                case 3:// TYPE_NETWORK ：网络
//                    break;
//                case 4:// TYPE_RAM_DISK ：闪存
//                    break;
//                case 5:// TYPE_CDROM ：光驱
//                    break;
//                case 6:// TYPE_SWAP ：页面交换
//                    break;
//                }
                System.out.println(fs.getDevName() + "读出：    " + usage.getDiskReads());
                System.out.println(fs.getDevName() + "写入：    " + usage.getDiskWrites());
            }
        } catch (Exception e) {

            e.printStackTrace();
        }

        return;
    }

    public static void net() throws Exception {
        Sigar sigar = new Sigar();
        String[] ifNames= sigar.getNetInterfaceList();
        for (int i = 0; i < ifNames.length; i++) {
            String name = ifNames[i];
            NetInterfaceConfig ifconfig = sigar.getNetInterfaceConfig(name);
            System.out.println("网络设备名:    " + name);
            System.out.println("IP地址:    " + ifconfig.getAddress());
            System.out.println("子网掩码:    " + ifconfig.getNetmask());
            if ((ifconfig.getFlags() & 1L) <= 0L) {
                System.out.println("!IFF_UP...skipping getNetInterfaceStat");
                continue;
            }
            NetInterfaceStat ifstat = sigar.getNetInterfaceStat(name);
            System.out.println(name + "接收的总包裹数:" + ifstat.getRxPackets());
            System.out.println(name + "发送的总包裹数:" + ifstat.getTxPackets());
            System.out.println(name + "接收到的总字节数:" + ifstat.getRxBytes());
            System.out.println(name + "发送的总字节数:" + ifstat.getTxBytes());
            System.out.println(name + "接收到的错误包数:" + ifstat.getRxErrors());
            System.out.println(name + "发送数据包时的错误数:" + ifstat.getTxErrors());
            System.out.println(name + "接收时丢弃的包数:" + ifstat.getRxDropped());
            System.out.println(name + "发送时丢弃的包数:" + ifstat.getTxDropped());
        }
    }

    public static void ethernet() throws SigarException {
        Sigar sigar = null;
        sigar = new Sigar();
        String[] ifaces = sigar.getNetInterfaceList();
        for (int i = 0; i < ifaces.length; i++) {
            NetInterfaceConfig cfg = sigar.getNetInterfaceConfig(ifaces[i]);
            if (NetFlags.LOOPBACK_ADDRESS.equals(cfg.getAddress()) || (cfg.getFlags() & NetFlags.IFF_LOOPBACK) != 0
                    || NetFlags.NULL_HWADDR.equals(cfg.getHwaddr())) {
                continue;
            }
            System.out.println(cfg.getName() + "IP地址:" + cfg.getAddress());
            System.out.println(cfg.getName() + "网关广播地址:" + cfg.getBroadcast());
            System.out.println(cfg.getName() + "网卡MAC地址:" + cfg.getHwaddr());
            System.out.println(cfg.getName() + "子网掩码:" + cfg.getNetmask());
            System.out.println(cfg.getName() + "网卡描述信息:" + cfg.getDescription());
            System.out.println(cfg.getName() + "网卡类型" + cfg.getType());
        }
    }

}
