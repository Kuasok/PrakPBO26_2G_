package id.ac.polinema.inheritance.tugas2;

public class Televisi {
    private String merk;
    private int jumlahChannel;
    private int channelAktif;

    public Televisi(String merk, int jumlahChannel) {
        this.merk = merk;
        this.jumlahChannel = jumlahChannel;
        this.channelAktif = 1;
    }

    public String getMerk() {
        return merk;
    }

    public int getJumlahChannel() {
        return jumlahChannel;
    }

    public int getChannelAktif() {
        return channelAktif;
    }

    public void pindahChannel(int channel) {
        if (channel >= 1 && channel <= jumlahChannel) {
            this.channelAktif = channel;
        }
    }

    public void info() {
        System.out.println("Merk           : " + merk);
        System.out.println("Jumlah Channel : " + jumlahChannel);
        System.out.println("Channel Aktif  : " + channelAktif);
    }
}
